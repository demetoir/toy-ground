import dotenv from "dotenv";
import express from "express";
import http from "http";
import cluster from "cluster";
import io from "socket.io";
import redisAdapter from "socket.io-redis";
import logger from "./logger.js";

dotenv.config();

const clusterId = process.env.pm_id || cluster.worker.id;
const containerId = process.env.CONTAINER_ID;

const port = 4001;
const app = express();
const httpServer = http.createServer(app, () => {
	logger.info(
		`start socket.io server at ${port} with ${process.env.NODE_ENV} mode`
	);
});

// const socketServer = io(httpServer, {transports: ["websocket"]});
const socketServer = io(httpServer);

socketServer.adapter(
	redisAdapter({
		host: "127.0.0.1",
		port: 6380,
	})
);

const NAME_SPACE = "event";
// const namedServer = socketServer.of(NAME_SPACE);

socketServer.on("connection", async socket => {
	const id = socket.id;

	// console.log(socketServer.nsps);
	// console.log(socketServer.sockets);

	logger.info(`cluster ${clusterId} id ${id} connected at /${NAME_SPACE}`);

	socket.on("hello", data => {
		console.log(
			`this is from cluster ${clusterId} of container ${containerId} data ${data}`
		);
		socket.broadcast.emit(
			"hello",
			`this is from cluster ${clusterId} of container ${containerId} data ${data}`
		);
	});

	socket.on("error", error =>
		logger.info(
			`cluster ${clusterId} error occur at socket id ${id} disconnected ${error}`
		)
	);

	socket.on("disconnecting", reason => {
		logger.info(
			`cluster ${clusterId} disconnecting at id ${id}, reason:${reason}`
		);
	});

	socket.on("disconnect", reason => {
		logger.info(
			`cluster ${clusterId} socket id ${id} disconnected reason:${reason}`
		);
	});
});

export default {app, server: httpServer, port};

import dotenv from "dotenv";
import express from "express";
import http from "http";
import io from "socket.io";
import logger from "./logger.js";

dotenv.config();

// https://woowabros.github.io/woowabros/2017/09/12/realtime-service.html
// https://blog.naver.com/tmondev/220982377777
// https://github.com/socketio/socket.io-redis/wiki/Session-ID-Socket-Rooms
// https://stackoverflow.com/questions/56162752/socket-io-with-redis-adapter-on-express-js-how-get-all-sockets-session-into-roo
// https://opens.kr/71

const port = 4001;
const app = express();
const httpServer = http.createServer(app).listen(port, () => {
	logger.info(
		`start socket.io server at ${port} with ${process.env.NODE_ENV} mode`
	);
});

const redisAdapter = require("socket.io-redis");

const socketServer = io(httpServer);
socketServer.adapter(redisAdapter({host: "redis_dev", port: 6379}));

const NAME_SPACE = "event";
// const namedServer = socketServer.of(NAME_SPACE);

const clustId = process.env.pm_id;
const containerId = process.env.CONTAINER_ID;

socketServer.on("connection", async socket => {
	const id = socket.id;

	logger.info(`id ${id} connected at /${NAME_SPACE}`);

	socket.on("hello", () => {
		console.log(
			`this is from cluster ${clustId} of container ${containerId}`
		);
		socket.send(
			`this is from cluster ${clustId} of container ${containerId}`
		);
	});

	socket.on("error", error =>
		logger.info(`error occur at socket id ${id} disconnected ${error}`)
	);
	socket.on("disconnecting", reason => {
		logger.info(`disconnecting at id ${id}, reason:${reason}`);
	});
	socket.on("disconnect", reason => {
		logger.info(`socket id ${id} disconnected reason:${reason}`);
	});
});

// noinspection JSUnusedGlobalSymbols
export default app;

const stickyCluster = require("sticky-cluster");

stickyCluster(
	// server initialization function
	callback => {
		// var http = require("http");
		// var app = require("express")();
		// var server = http.createServer(app);

		const {server} = require("./app.js").default;

		// configure an app
		// do some async stuff if needed

		// don't do server.listen(), just pass the server instance into the callback
		callback(server);
	},

	// options
	{
		concurrency: 2,
		port: 4001,
		debug: false,
		env(index) {
			return {stickycluster_worker_index: index};
		},
	},
);

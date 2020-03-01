import express from "express";

const app = express();

app.get("/", (req: express.Request, res: express.Response) =>
  res.send("hello")
);

export default app;

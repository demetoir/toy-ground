import app from "@src/app";
import dotenv from "dotenv";

dotenv.config();

const port = process.env.DEV_PORT;

app.listen(port, () => {
  console.log(`server start at localhost:${port}`);
});

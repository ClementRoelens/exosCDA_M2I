import clientRouter from "./routes/client.route";
import commandRouter from "./routes/order.route";
import productRouter from "./routes/product.route";

const express = require("express");

const app = express();

app.use(express.json());

app.use("/products",productRouter);
app.use("/clients",clientRouter);
app.use("/commands",commandRouter);

app.listen(3000 , () => console.log("Écoute sur 3000"));

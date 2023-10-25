"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const client_route_1 = __importDefault(require("./routes/client.route"));
const command_route_1 = __importDefault(require("./routes/command.route"));
const product_route_1 = __importDefault(require("./routes/product.route"));
const express = require("express");
const app = express();
app.use(express.json());
app.use("/products", product_route_1.default);
app.use("/clients", client_route_1.default);
app.use("/commands", command_route_1.default);
app.listen(3000, () => console.log("Écoute sur 3000"));
//# sourceMappingURL=index.js.map
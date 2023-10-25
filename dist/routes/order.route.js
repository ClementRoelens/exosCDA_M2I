"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const OrderDao_1 = require("../models/OrderDao");
const path_1 = require("path");
const crypto_1 = __importDefault(require("crypto"));
const orderRouter = express_1.default.Router();
const dao = new OrderDao_1.OrderDao((0, path_1.resolve)("db/ordersDb.json"));
function typeCheck(body) {
    const anonymousOrder = {
        products: [],
        client: {
            id: "",
            firstname: "",
            lastname: "",
            phoneNumber: ""
        }
    };
    for (const key in anonymousOrder) {
        if (!(key in body) || (typeof body[key] !== typeof anonymousOrder[key])) {
            return false;
        }
    }
    return true;
}
orderRouter.get("/", (req, res) => {
    res.status(200).json({ orders: dao.getOrders() });
});
orderRouter.get("/:id", (req, res) => {
    const foundOrder = dao.getOneOrder(req.params.id);
    if (foundOrder) {
        res.status(200).json({ order: foundOrder });
    }
    else {
        res.status(404).json({ message: "Aucune ordere trouvé" });
    }
});
orderRouter.post("/", (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    if (typeCheck(req.body)) {
        try {
            const newOrder = {
                id: crypto_1.default.randomUUID(),
                client: req.body.client,
                products: req.body.products
            };
            const response = yield dao.addOrder(newOrder);
            if (typeof response === "string") {
                res.status(500).json({ messsage: "Le produit n'a pas pu être ajouté" });
            }
            else {
                res.status(201).json({ order: newOrder });
            }
        }
        catch (err) {
            console.error(err.message);
        }
    }
    else {
        res.status(400).json({ message: "Mauvaise structure de données. La structure attendue est : { 'products' : Product[] ,'client' : Client }" });
    }
}));
exports.default = orderRouter;
//# sourceMappingURL=order.route.js.map
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
const ProductDao_1 = require("../models/ProductDao");
const path_1 = require("path");
const crypto_1 = __importDefault(require("crypto"));
const productRouter = express_1.default.Router();
const dao = new ProductDao_1.ProductDao((0, path_1.resolve)("db/productsDb.json"));
function typeCheck(body) {
    const anonymousProduct = {
        title: "",
        stock: 0,
        price: 0
    };
    for (const key in anonymousProduct) {
        if (!(key in body) || (typeof body[key] !== typeof anonymousProduct[key])) {
            return false;
        }
    }
    return true;
}
productRouter.get("/", (req, res) => {
    res.status(200).json({ products: dao.getProducts() });
});
productRouter.get("/:id", (req, res) => {
    const foundProduct = dao.getOneProduct(req.params.id);
    if (foundProduct) {
        res.status(200).json({ product: foundProduct });
    }
    else {
        res.status(404).json({ message: "Aucun produit trouvé" });
    }
});
productRouter.post("/", (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    if (typeCheck(req.body)) {
        try {
            const newProduct = {
                id: crypto_1.default.randomUUID(),
                title: req.body.title,
                price: req.body.price,
                stock: req.body.stock
            };
            const response = yield dao.addProduct(newProduct);
            if (typeof response === "string") {
                res.status(500).json({ messsage: "Le produit n'a pas pu être ajouté" });
            }
            else {
                res.status(201).json({ product: newProduct });
            }
        }
        catch (err) {
            console.error(err.message);
            res.status(400).json({ message: err.message });
        }
    }
    else {
        res.status(400).json({ message: "Mauvaise structure de données. La structure attendue est : { 'title' : string ,'price' : number , 'stock' : number }" });
    }
}));
exports.default = productRouter;
//# sourceMappingURL=product.route.js.map
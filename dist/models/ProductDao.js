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
Object.defineProperty(exports, "__esModule", { value: true });
exports.ProductDao = void 0;
const promises_1 = require("fs/promises");
class ProductDao {
    constructor(_filePath) {
        this._filePath = _filePath;
        this._products = [];
        this.initialize();
    }
    initialize() {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                yield (0, promises_1.access)(this._filePath, promises_1.constants.R_OK);
                this._products = JSON.parse((yield (0, promises_1.readFile)(this._filePath)).toString("utf-8")).products;
            }
            catch (err) {
                console.error("Erreur lors de la récupération des produits");
            }
        });
    }
    getProducts() {
        return this._products;
    }
    getOneProduct(id) {
        return this._products.find((product) => product.id === id);
    }
    addProduct(product) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                this._products.push(product);
                yield (0, promises_1.writeFile)(this._filePath, JSON.stringify({ products: this._products }), "utf-8");
                return product;
            }
            catch (err) {
                console.error("Erreur lors de l'ajout d'un produit : ", err);
                return err.message;
            }
        });
    }
}
exports.ProductDao = ProductDao;
//# sourceMappingURL=ProductDao.js.map
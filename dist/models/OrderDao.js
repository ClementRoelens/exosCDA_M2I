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
exports.OrderDao = void 0;
const promises_1 = require("fs/promises");
class OrderDao {
    constructor(_filePath) {
        this._filePath = _filePath;
        this._orders = [];
        this.initialize();
    }
    initialize() {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                yield (0, promises_1.access)(this._filePath, promises_1.constants.R_OK);
                this._orders = JSON.parse((yield (0, promises_1.readFile)(this._filePath)).toString("utf-8")).orders;
            }
            catch (err) {
                console.error("Erreur lors de la récupération des orderes");
            }
        });
    }
    getOrders() {
        return this._orders;
    }
    getOneOrder(id) {
        return this._orders.find((order) => order.id === id);
    }
    addOrder(order) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                this._orders.push(order);
                yield (0, promises_1.writeFile)(this._filePath, JSON.stringify({ orders: this._orders }), "utf-8");
                return order;
            }
            catch (err) {
                console.error("Erreur lors de l'ajout d'une ordere : ", err);
                return err.message;
            }
        });
    }
}
exports.OrderDao = OrderDao;
//# sourceMappingURL=OrderDao.js.map
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
const ClientDao_1 = require("../models/ClientDao");
const path_1 = require("path");
const crypto_1 = __importDefault(require("crypto"));
const clientRouter = express_1.default.Router();
const dao = new ClientDao_1.ClientDao((0, path_1.resolve)("db/clientsDb.json"));
clientRouter.get("/", (req, res) => {
    res.status(200).json({ clients: dao.getClients() });
});
clientRouter.get("/:id", (req, res) => {
    const foundClient = dao.getOneClient(req.params.id);
    if (foundClient) {
        res.status(200).json({ client: foundClient });
    }
    else {
        res.status(404).json({ message: "Aucun client trouvé" });
    }
});
clientRouter.post("/", (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        const newClient = {
            id: crypto_1.default.randomUUID(),
            firstname: req.body.firstname,
            lastname: req.body.lastname,
            phoneNumber: req.body.phoneNumber
        };
        const response = yield dao.addClient(newClient);
        if (typeof response === "string") {
            res.status(500).json({ messsage: "Le produit n'a pas pu être ajouté" });
        }
        else {
            res.status(201).json({ client: newClient });
        }
    }
    catch (err) {
        console.error(err.message);
    }
}));
exports.default = clientRouter;
//# sourceMappingURL=client.route.js.map
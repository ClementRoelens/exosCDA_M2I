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
const CommandDao_1 = require("../models/CommandDao");
const path_1 = require("path");
const crypto_1 = __importDefault(require("crypto"));
const commandRouter = express_1.default.Router();
const dao = new CommandDao_1.CommandDao((0, path_1.resolve)("db/commandsDb.json"));
commandRouter.get("/", (req, res) => {
    res.status(200).json({ commands: dao.getCommands() });
});
commandRouter.get("/:id", (req, res) => {
    const foundCommand = dao.getOneCommand(req.params.id);
    if (foundCommand) {
        res.status(200).json({ command: foundCommand });
    }
    else {
        res.status(404).json({ message: "Aucune commande trouvé" });
    }
});
commandRouter.post("/", (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        const newCommand = {
            id: crypto_1.default.randomUUID(),
            client: req.body.client,
            products: req.body.products
        };
        const response = yield dao.addCommand(newCommand);
        if (typeof response === "string") {
            res.status(500).json({ messsage: "Le produit n'a pas pu être ajouté" });
        }
        else {
            res.status(201).json({ command: newCommand });
        }
    }
    catch (err) {
        console.error(err.message);
    }
}));
exports.default = commandRouter;
//# sourceMappingURL=command.route.js.map
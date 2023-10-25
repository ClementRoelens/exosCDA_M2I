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
exports.CommandDao = void 0;
const promises_1 = require("fs/promises");
class CommandDao {
    constructor(_filePath) {
        this._filePath = _filePath;
        this._commands = [];
        this.initialize();
    }
    initialize() {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                yield (0, promises_1.access)(this._filePath, promises_1.constants.R_OK);
                this._commands = JSON.parse((yield (0, promises_1.readFile)(this._filePath)).toString("utf-8")).commands;
            }
            catch (err) {
                console.error("Erreur lors de la récupération des commandes");
            }
        });
    }
    getCommands() {
        return this._commands;
    }
    getOneCommand(id) {
        return this._commands.find((command) => command.id === id);
    }
    addCommand(command) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                this._commands.push(command);
                yield (0, promises_1.writeFile)(this._filePath, JSON.stringify({ commands: this._commands }), "utf-8");
                return command;
            }
            catch (err) {
                console.error("Erreur lors de l'ajout d'une commande : ", err);
                return err.message;
            }
        });
    }
}
exports.CommandDao = CommandDao;
//# sourceMappingURL=CommandDao.js.map
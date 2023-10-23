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
exports.Ihm = void 0;
const Game_js_1 = require("./Game.js");
const Tools_js_1 = require("./Tools.js");
class Ihm {
    constructor() {
        this._game = new Game_js_1.Game();
        console.log("\nUne nouvelle partie commence !");
        console.log("À toi de jouer joueur 1");
    }
    turn() {
        return __awaiter(this, void 0, void 0, function* () {
            let x = "";
            while (x !== "0" && x !== "1" && x !== "2") {
                x = (yield (0, Tools_js_1.input)("Entrez l'abscisse de votre coup\n"));
                if (isNaN(+x) || (+x < 0) || (+x > 2)) {
                    console.log("Entrez un chiffre entre 0 et 2\n");
                }
            }
            let y = "";
            while (y !== "0" && y !== "1" && y !== "2") {
                y = (yield (0, Tools_js_1.input)("Entrez l'ordonnée de votre coup\n"));
                if (isNaN(+y) || (+y < 0) || (+y > 2)) {
                    console.log("Entrez un chiffre entre 0 et 2\n");
                }
            }
            console.log(`Vous avez joué le coup ${x},${y}`);
            console.log(this._game.play(+x, +y));
            // C'est ma première fonction récursive
            if (!this._game.isOver) {
                this.turn();
            }
        });
    }
}
exports.Ihm = Ihm;

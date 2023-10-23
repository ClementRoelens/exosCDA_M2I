import { Game } from "./Game.js";
import { input } from "./Tools.js";

export class Ihm {
    private _game: Game;

    constructor() {
        this._game = new Game();
        console.log("\nUne nouvelle partie commence !");
        console.log("À toi de jouer joueur 1");
    }

    async turn() {
        let x = "";
        while (x !== "0" && x !== "1" && x !== "2") {
            x = await input("Entrez l'abscisse de votre coup\n") as string;
            if (isNaN(+x) || (+x < 0) || (+x > 2)) {
                console.log("Entrez un chiffre entre 0 et 2\n");
            }
        }

        let y = "";
        while (y !== "0" && y !== "1" && y !== "2") {
            y = await input("Entrez l'ordonnée de votre coup\n") as string;
            if (isNaN(+y) || (+y < 0) || (+y > 2)) {
                console.log("Entrez un chiffre entre 0 et 2\n");
            }
        }

        console.log(`Vous avez joué le coup ${x},${y}`);

        console.log(this._game.play(+x,+y));

        // C'est ma première fonction récursive
        if (!this._game.isOver){
            this.turn();
        }
    }
}
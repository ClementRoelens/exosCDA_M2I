"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Game = void 0;
class Game {
    constructor() {
        this._limit = 9;
        this._board = [
            ["", "", ""],
            ["", "", ""],
            ["", "", ""]
        ];
        this._playerTurn = 1;
        this._isOver = false;
        this._count = 0;
    }
    play(x, y) {
        if (!this._isOver) {
            if (this._board[y][x] === "") {
                this._count++;
                this._board[y][x] = (this._playerTurn === 1) ? "X" : "O";
                if (this.isWon()) {
                    this._isOver = true;
                    return this.drawBoard() + "Félicitations joueur " + this._playerTurn + ", vous avez gagné !";
                }
            }
            else {
                return this.drawBoard() + "Cette case est déjà occupée";
            }
            this._playerTurn = (this._playerTurn === 1) ? 2 : 1;
            if (this._count === this._limit) {
                this._isOver = true;
                return this.drawBoard() + "Égalité, la partie est finie";
            }
            return this.drawBoard() + "À toi de jouer joueur " + this._playerTurn;
        }
        return this.drawBoard() + "La partie est finie";
    }
    isWon() {
        for (let i = 0; i < 3; i++) {
            if (
            // On check chaque ligne 
            ((this._board[i][0] === this._board[i][1]) && (this._board[i][0] === this._board[i][2]) && (this._board[i][0] !== ""))
                ||
                    //et chaque colonne
                    ((this._board[0][i] === this._board[1][i]) && (this._board[0][i] === this._board[2][i]) && (this._board[0][i] !== ""))) {
                return true;
            }
        }
        if (
        // Et les deux diagonales
        ((this._board[0][0] === this._board[1][1]) && (this._board[0][0] === this._board[2][2]) && (this._board[0][0] !== ""))
            ||
                ((this._board[0][2] === this._board[1][1]) && (this._board[0][2] === this._board[2][0]) && (this._board[0][2] !== ""))) {
            return true;
        }
        return false;
    }
    get playerTurn() {
        return this._playerTurn;
    }
    get isOver() {
        return this._isOver;
    }
    drawBoard() {
        let draw = "";
        this._board.forEach((line) => {
            draw += "\n| ";
            line.forEach((cell) => {
                draw += (cell === "") ? "_" : cell;
                draw += " | ";
            });
        });
        draw += "\n\n";
        return draw;
    }
}
exports.Game = Game;

import { Command } from "./Command";
import { readFile, writeFile, access, constants } from "fs/promises";

export class CommandDao {
    private _commands: Command[];

    constructor(private _filePath: string) {
        this._commands = [];
        this.initialize();
    }

    async initialize() {
        try {
            await access(this._filePath, constants.R_OK);
            this._commands = JSON.parse((await readFile(this._filePath)).toString("utf-8")).commands;
        }
        catch (err: any) {
            console.error("Erreur lors de la récupération des commandes");
        }
    }

    getCommands(): Command[] {
        return this._commands;
    }

    getOneCommand(id: string): Command | undefined {
        return this._commands.find((command: Command) => command.id === id);
    }

    async addCommand(command: Command): Promise<Command | string> {
        try {
            this._commands.push(command);
            await writeFile(this._filePath, JSON.stringify({commands : this._commands}), "utf-8");
            return command;
        }
        catch (err: any) {
            console.error("Erreur lors de l'ajout d'une commande : ", err);
            return err.message;
        }
    }
}
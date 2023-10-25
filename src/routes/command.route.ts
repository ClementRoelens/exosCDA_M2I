import express, { Request, Response } from "express";
import { CommandDao } from "../models/CommandDao";
import { resolve } from "path";
import { Command } from "../models/Command";
import crypto from "crypto";

const commandRouter = express.Router();
const dao = new CommandDao(resolve("db/commandsDb.json"));


commandRouter.get("/", (req: Request, res: Response) => {
    res.status(200).json({ commands: dao.getCommands() });
});

commandRouter.get("/:id", (req: Request, res: Response) => {
    const foundCommand = dao.getOneCommand(req.params.id);

    if (foundCommand) {
        res.status(200).json({ command: foundCommand });
    } else {
        res.status(404).json({ message: "Aucune commande trouvé" });
    }
});

commandRouter.post("/", async (req: Request, res: Response) => {
    try {
        const newCommand: Command = {
            id: crypto.randomUUID(),
            client : req.body.client,
            products : req.body.products
        };
        const response = await dao.addCommand(newCommand);
        if (typeof response === "string") {
            res.status(500).json({ messsage: "Le produit n'a pas pu être ajouté" });
        } else {
            res.status(201).json({ command: newCommand });
        }
    }
    catch (err: any) {
        console.error(err.message);
    }
});

export default commandRouter;
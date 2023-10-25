import express, { Request, Response } from "express";
import { CommandDao } from "../models/CommandDao";
import { resolve } from "path";
import { Command } from "../models/Command";
import crypto from "crypto";

const commandRouter = express.Router();
const dao = new CommandDao(resolve("db/commandsDb.json"));


function typeCheck(body:any) : boolean {
    const anonymousCommand:Command = {
        products:[],
        client : {
            id:"",
            firstname:"",
            lastname:"",
            phoneNumber:""
        }
    };

    for (const key in anonymousCommand) {
        if (!(key in body) || (typeof body[key] !== typeof anonymousCommand[key])){
            return false;
        }
    }
    return true;
}

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
    if (typeCheck(req.body)){
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
    } else {
        res.status(400).json({message : "Mauvaise structure de données. La structure attendue est : { 'products' : Product[] ,'client' : Client }"});
    }
});

export default commandRouter;
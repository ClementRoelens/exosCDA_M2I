import express, { Request, Response } from "express";
import { ClientDao } from "../models/ClientDao";
import { resolve } from "path";
import { Client } from "../models/Client";
import crypto from "crypto";

const clientRouter = express.Router();
const dao = new ClientDao(resolve("db/clientsDb.json"));


function typeCheck(body:any) : boolean {
    const anonymousClient:Client = {
        firstname:"",
        lastname:"",
        phoneNumber:""
    };

    for (const key in anonymousClient) {
        if (!(key in body) || (typeof body[key] !== typeof anonymousClient[key])){
            return false;
        }
    }
    return true;
}

clientRouter.get("/", (req: Request, res: Response) => {
    res.status(200).json({ clients: dao.getClients() });
});

clientRouter.get("/:id", (req: Request, res: Response) => {
    const foundClient = dao.getOneClient(req.params.id);

    if (foundClient) {
        res.status(200).json({ client: foundClient });
    } else {
        res.status(404).json({ message: "Aucun client trouvé" });
    }
});

clientRouter.post("/", async (req: Request, res: Response) => {
    if (typeCheck(req.body)){
        try {
            const newClient: Client = {
                id: crypto.randomUUID(),
                firstname: req.body.firstname,
                lastname: req.body.lastname,
                phoneNumber: req.body.phoneNumber
            };
            const response = await dao.addClient(newClient);
            if (typeof response === "string") {
                res.status(500).json({ messsage: "Le produit n'a pas pu être ajouté" });
            } else {
                res.status(201).json({ client: newClient });
            }
        }
        catch (err: any) {
            console.error(err.message);
        }
    } else {
        res.status(400).json({message : "Mauvaise structure de données. La structure attendue est : { 'firstname' : string , 'lastname' : string , 'phoneNumber' : string }"});
    }
});

export default clientRouter;
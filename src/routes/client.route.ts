import express, { Request, Response } from "express";
import { ClientDao } from "../models/ClientDao";
import { resolve } from "path";
import { Client } from "../models/Client";
import crypto from "crypto";

const clientRouter = express.Router();
const dao = new ClientDao(resolve("db/clientsDb.json"));


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
});

export default clientRouter;
import express, { Request, Response } from "express";
import { OrderDao } from "../models/OrderDao";
import { resolve } from "path";
import { Order } from "../models/Order";
import crypto from "crypto";

const orderRouter = express.Router();
const dao = new OrderDao(resolve("db/ordersDb.json"));


function typeCheck(body:any) : boolean {
    const anonymousOrder:Order = {
        products:[],
        client : {
            id:"",
            firstname:"",
            lastname:"",
            phoneNumber:""
        }
    };

    for (const key in anonymousOrder) {
        if (!(key in body) || (typeof body[key] !== typeof anonymousOrder[key])){
            return false;
        }
    }
    return true;
}

orderRouter.get("/", (req: Request, res: Response) => {
    res.status(200).json({ orders: dao.getOrders() });
});

orderRouter.get("/:id", (req: Request, res: Response) => {
    const foundOrder = dao.getOneOrder(req.params.id);

    if (foundOrder) {
        res.status(200).json({ order: foundOrder });
    } else {
        res.status(404).json({ message: "Aucune ordere trouvé" });
    }
});

orderRouter.post("/", async (req: Request, res: Response) => {
    if (typeCheck(req.body)){
        try {
            const newOrder: Order = {
                id: crypto.randomUUID(),
                client : req.body.client,
                products : req.body.products
            };
            const response = await dao.addOrder(newOrder);
            if (typeof response === "string") {
                res.status(500).json({ messsage: "Le produit n'a pas pu être ajouté" });
            } else {
                res.status(201).json({ order: newOrder });
            }
        }
        catch (err: any) {
            console.error(err.message);
        }
    } else {
        res.status(400).json({message : "Mauvaise structure de données. La structure attendue est : { 'products' : Product[] ,'client' : Client }"});
    }
});

export default orderRouter;
import express, { Request, Response } from "express";
import { ProductDao } from "../models/ProductDao";
import { resolve } from "path";
import { Product } from "../models/Product";
import crypto from "crypto"

const productRouter = express.Router();
const dao = new ProductDao(resolve("db/productsDb.json"));

// On verra après...

// function typeCheck(body:any, acceptedKeys:string[]) : boolean {
//     for (const key in body){
//         if (!acceptedKeys.includes(key)){
//             return false;
//         }
//     }

//     for (const key of acceptedKeys) {
//         if (!(key in body)){
//             return false;
//         }
//     }
//     return true;
// }

productRouter.get("/", (req: Request, res: Response) => {
    res.status(200).json({ products: dao.getProducts() });
});

productRouter.get("/:id", (req: Request, res: Response) => {
    const foundProduct = dao.getOneProduct(req.params.id);

    if (foundProduct) {
        res.status(200).json({ product: foundProduct });
    } else {
        res.status(404).json({ message: "Aucun produit trouvé" });
    }
});

productRouter.post("/", async (req: Request, res: Response) => {
    try {
        const newProduct: Product = {
            id: crypto.randomUUID(),
            title: req.body.title,
            price: req.body.price,
            stock: req.body.stock
        };
        const response = await dao.addProduct(newProduct);
        if (typeof response === "string") {
            res.status(500).json({messsage : "Le produit n'a pas pu être ajouté"});
        } else {
            res.status(201).json({product : newProduct});
        }
    }
    catch (err: any) {
        console.error(err.message);
        res.status(400).json({message : err.message});
    }
});

export default productRouter;
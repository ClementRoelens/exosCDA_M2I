import express, { Request, Response } from "express";
import { ProductDao } from "../models/ProductDao";
import { resolve } from "path";
import { Product } from "../models/Product";
import crypto from "crypto"

const productRouter = express.Router();
const dao = new ProductDao(resolve("db/productsDb.json"));


function typeCheck(body:any) : boolean {
    const anonymousProduct:Product = {
        title:"",
        stock:0,
        price:0
    };

    for (const key in anonymousProduct) {
        if (!(key in body) || (typeof body[key] !== typeof anonymousProduct[key])){
            return false;
        }
    }
    return true;
}

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
    if (typeCheck(req.body)){
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
    } else {
        res.status(400).json({message : "Mauvaise structure de données. La structure attendue est : { 'title' : string ,'price' : number , 'stock' : number }"});
    }
});

export default productRouter;
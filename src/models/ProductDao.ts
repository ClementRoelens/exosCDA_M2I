import { Product } from "./Product";
import { readFile, writeFile, access, constants } from "fs/promises";

export class ProductDao {
    private _products: Product[];

    constructor(private _filePath: string) {
        this._products = [];
        this.initialize();
    }

    async initialize(){
        try {
            await access(this._filePath, constants.R_OK);
            this._products = JSON.parse((await readFile(this._filePath)).toString("utf-8")).products;
        } 
        catch (err:any){
            console.error("Erreur lors de la récupération des produits");
        }
    }

    getProducts(): Product[] {
        return this._products;
    }

    getOneProduct(id: string): Product | undefined {
        return this._products.find((product: Product) => product.id === id);
    }

    async addProduct(product: Product): Promise<Product | string> {
        try {
            this._products.push(product);
            await writeFile(this._filePath, JSON.stringify({products : this._products}), "utf-8");
            return product;
        } 
        catch (err:any) {
            console.error("Erreur lors de l'ajout d'un produit : ",err);
            return err.message;
        }
    }
}
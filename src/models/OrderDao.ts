import { Order } from "./Order";
import { readFile, writeFile, access, constants } from "fs/promises";

export class OrderDao {
    private _orders: Order[];

    constructor(private _filePath: string) {
        this._orders = [];
        this.initialize();
    }

    async initialize() {
        try {
            await access(this._filePath, constants.R_OK);
            this._orders = JSON.parse((await readFile(this._filePath)).toString("utf-8")).orders;
        }
        catch (err: any) {
            console.error("Erreur lors de la récupération des orderes");
        }
    }

    getOrders(): Order[] {
        return this._orders;
    }

    getOneOrder(id: string): Order | undefined {
        return this._orders.find((order: Order) => order.id === id);
    }

    async addOrder(order: Order): Promise<Order | string> {
        try {
            this._orders.push(order);
            await writeFile(this._filePath, JSON.stringify({orders : this._orders}), "utf-8");
            return order;
        }
        catch (err: any) {
            console.error("Erreur lors de l'ajout d'une ordere : ", err);
            return err.message;
        }
    }
}
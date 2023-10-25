import { Client } from "./Client";
import { readFile, writeFile, access, constants } from "fs/promises";

export class ClientDao{
    private _clients:Client[];

    constructor(private _filePath:string){
        this._clients = [];
        this.initialize();
    }

    async initialize(){
        try {
            await access(this._filePath, constants.R_OK);
            this._clients = JSON.parse((await readFile(this._filePath)).toString("utf-8")).clients;
        } 
        catch (err:any){
            console.error("Erreur lors de la récupération des clients");
        }
    }

    getClients(): Client[] {
        return this._clients;
    }

    getOneClient(id: string): Client | undefined {
        return this._clients.find((client: Client) => client.id === id);
    }

    async addClient(client: Client): Promise<Client | string> {
        try {
            this._clients.push(client);
            await writeFile(this._filePath, JSON.stringify({clients : this._clients}), "utf-8");
            return client;
        } 
        catch (err:any) {
            console.error("Erreur lors de l'ajout d'un client : ",err);
            return err.message;
        }
    }
}
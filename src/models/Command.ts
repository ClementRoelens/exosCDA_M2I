import { Client } from "./Client";
import { Product } from "./Product";

export interface Command {
    id?:string;
    client:Client;
    products:Product[]
}
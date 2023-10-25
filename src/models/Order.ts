import { Client } from "./Client";
import { Product } from "./Product";

export interface Order {
    id?:string;
    client:Client;
    products:Product[]
}
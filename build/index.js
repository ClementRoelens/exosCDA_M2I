"use strict";
// import { IncomingMessage, ServerResponse } from "http";
Object.defineProperty(exports, "__esModule", { value: true });
const Ihm_1 = require("./classes/Ihm");
// const http = require("http");
// http.createServer((request:IncomingMessage,response:ServerResponse) => {
//     console.log("En attente de requêtes");
//     let body: Buffer[] = [];
//     request.on("data",chunk => {
//         body.push(chunk);
//     })
//     .on("end", () => {
//         response.end(Buffer.concat(body).toString());
//     })
//     .on("error", error => {
//         console.error(error);
//     })
// }).listen(8080);
const ihm = new Ihm_1.Ihm();
ihm.turn();

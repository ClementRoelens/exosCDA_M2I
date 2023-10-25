"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
const express = require("express");
const { access, constants, readFile, writeFile } = require("fs/promises");
const app = express();
const dataFile = "todos.json";
function readTodos() {
    return __awaiter(this, void 0, void 0, function* () {
        yield access(dataFile, constants.R_OK);
        return JSON.parse(yield readFile(dataFile)).todos;
    });
}
function typeCheck(body, acceptedKeys) {
    for (const key in body) {
        if (!acceptedKeys.includes(key)) {
            return false;
        }
    }
    for (const key of acceptedKeys) {
        if (!(key in body)) {
            return false;
        }
    }
    return true;
}
app.use(express.json());
app.get("/todos", (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        const todos = yield readTodos();
        res.status(200).json(todos);
    }
    catch (err) {
        if (err.code === "ENOENT") {
            console.error("Erreur, pas d'accès aux données\n", err.message);
            res.status(500).json({ message: err.message });
        }
        console.error("Erreur lors de la lecture des données\n", err.message);
        res.status(500).json({ message: err.message });
    }
}));
app.get("/todos/:id", (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    if (!isNaN(+req.params.id)) {
        try {
            const todos = yield readTodos();
            const todo = todos.find(todo => todo.id === +req.params.id);
            if (todo) {
                res.status(200).json(todo);
            }
            else {
                console.error(`La todo d'id ${req.params.id} n'a pas été trouvée`);
                res.status(404).json({ message: "Cet id ne correspond à aucune todo" });
            }
        }
        catch (err) {
            if (err.code === "ENOENT") {
                console.error("Erreur, pas d'accès aux données\n", err.message);
                res.status(500).json({ message: err.message });
            }
            console.error("Erreur lors de la lecture des données\n", err.message);
            res.status(500).json({ message: err.message });
        }
    }
    else {
        res.status(400).json({ message: "Le paramètre doit être un nombre" });
    }
}));
app.post("/todos", (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    if (typeCheck(req.body, ["title", "isDone"])) {
        try {
            const todos = yield readTodos();
            const newId = (todos[todos.length - 1].id) + 1;
            const newTodo = Object.assign(Object.assign({}, req.body), { id: newId });
            todos.push(newTodo);
            yield writeFile(dataFile, JSON.stringify({ todos: todos }));
            res.status(201).json(newTodo);
        }
        catch (err) {
            console.error("Erreur lors de la création d'une todo\n", err.message);
            res.status(500).json({ message: err.message });
        }
    }
    else {
        console.error("Le corps de la requête est mauvais");
        res.status(400).json({ message: "Mauvaise requête. Vous devez passer un objet de type {'title':string , 'isDone':boolean}" });
    }
}));
app.patch("/todos/:id", (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    if (!isNaN(+req.params.id)) {
        try {
            const todos = yield readTodos();
            const index = todos.findIndex(todo => todo.id === +req.params.id);
            if (index !== -1) {
                todos[index] = Object.assign(Object.assign({}, todos[index]), req.body);
                yield writeFile(dataFile, JSON.stringify({ todos: todos }));
                res.status(200).json({ message: "todo correctement modifiée", todos: todos });
            }
            else {
                console.error("Toto non trouvée");
                res.status(404).json({ message: "todo non trouvée" });
            }
        }
        catch (err) {
        }
    }
    else {
        res.status(400).json({ message: "Le paramètre doit être un nombre" });
    }
}));
app.delete("/todos/:id", (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    if (!isNaN(+req.params.id)) {
        try {
            const todos = yield readTodos();
            const index = todos.findIndex(todo => todo.id === +req.params.id);
            if (index !== -1) {
                todos.splice(index, 1);
                yield writeFile(dataFile, JSON.stringify({ todos: todos }));
                res.status(200).json({ message: "La todo a bien été supprimée", todos: todos });
            }
            else {
                res.status(404).json({ message: "La todo à supprimer n'a pas été trouvée" });
            }
        }
        catch (err) {
            console.error(`Erreur lors de la suppression de la todo ${req.params.id}\n`, err.message);
            res.status(500).json({ message: "Erreur lors de la suppression" });
        }
    }
    else {
        res.status(400).json({ message: "Le paramètre doit être un nombre" });
    }
}));
app.listen("3000", () => {
    console.log("http://127.0.0.1:3000");
});

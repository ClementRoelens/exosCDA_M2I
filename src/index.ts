import { Todo } from "./classes/Todo";
import { Request, Response, NextFunction } from "express";

const express = require("express");
const { access, constants, readFile, writeFile } = require("fs/promises");

const app = express();
const dataFile = "todos.json";


async function readTodos(): Promise<Todo[]> {
    await access(dataFile, constants.R_OK);

    return JSON.parse(await readFile(dataFile)).todos as Todo[];
}

function typeCheck(body:any, acceptedKeys:string[]) : boolean {
    for (const key in body){
        if (!acceptedKeys.includes(key)){
            return false;
        }
    }
    
    for (const key of acceptedKeys) {
        if (!(key in body)){
            return false;
        }
    }
    return true;
}


app.use(express.json());

app.get("/todos", async (req: Request, res: Response) => {
    try {
        const todos = await readTodos();
        res.status(200).json(todos);
    }
    catch (err: any) {
        if (err.code === "ENOENT") {
            console.error("Erreur, pas d'accès aux données\n", err.message);
            res.status(500).json({ message: err.message });
        }
        console.error("Erreur lors de la lecture des données\n", err.message);
        res.status(500).json({ message: err.message });
    }
});

app.get("/todos/:id", async (req: Request, res: Response) => {
    if (!isNaN(+req.params.id)) {
        try {
            const todos = await readTodos();
            const todo = todos.find(todo => todo.id === +req.params.id);

            if (todo) {
                res.status(200).json(todo);
            } else {
                console.error(`La todo d'id ${req.params.id} n'a pas été trouvée`);
                res.status(404).json({ message: "Cet id ne correspond à aucune todo" })
            }
        }
        catch (err: any) {
            if (err.code === "ENOENT") {
                console.error("Erreur, pas d'accès aux données\n", err.message);
                res.status(500).json({ message: err.message });
            }
            console.error("Erreur lors de la lecture des données\n", err.message);
            res.status(500).json({ message: err.message });
        }
    } else {
        res.status(400).json({ message: "Le paramètre doit être un nombre" });
    }
});

app.post("/todos", async (req: Request, res: Response) => {
    if (typeCheck(req.body,["title","isDone"])){
        try {
            const todos = await readTodos();
            const newId = (todos[todos.length - 1].id) + 1;
            const newTodo = { ...req.body, id: newId } as Todo;
            todos.push(newTodo);
    
            await writeFile(dataFile, JSON.stringify({ todos: todos }));
            res.status(201).json(newTodo);
        }
        catch (err: any) {
            console.error("Erreur lors de la création d'une todo\n", err.message);
            res.status(500).json({ message: err.message });
        }
    } else {
        console.error("Le corps de la requête est mauvais");
        res.status(400).json({message:"Mauvaise requête. Vous devez passer un objet de type {'title':string , 'isDone':boolean}"});
    }
});

app.patch("/todos/:id", async (req: Request, res: Response) => {
    if (!isNaN(+req.params.id)) {
        try {
            const todos = await readTodos();
            const index = todos.findIndex(todo => todo.id === +req.params.id);
            if (index !== -1) {
                todos[index] = { ...todos[index], ...req.body };
                await writeFile(dataFile, JSON.stringify({todos:todos}));
                res.status(200).json({ message: "todo correctement modifiée", todos: todos });
            } else {
                console.error("Toto non trouvée");
                res.status(404).json({ message: "todo non trouvée" });
            }


        } catch (err: any) {

        }
    } else {
        res.status(400).json({ message: "Le paramètre doit être un nombre" });
    }
});

app.delete("/todos/:id", async (req: Request, res: Response) => {
    if (!isNaN(+req.params.id)) {
        try {
            const todos = await readTodos();
            const index = todos.findIndex(todo => todo.id === +req.params.id);

            if (index !== -1) {
                todos.splice(index, 1);
                await writeFile(dataFile, JSON.stringify({ todos: todos }));
                res.status(200).json({ message: "La todo a bien été supprimée", todos: todos });
            } else {
                res.status(404).json({ message: "La todo à supprimer n'a pas été trouvée" });
            }
        }
        catch (err: any) {
            console.error(`Erreur lors de la suppression de la todo ${req.params.id}\n`, err.message);
            res.status(500).json({ message: "Erreur lors de la suppression" });
        }
    } else {
        res.status(400).json({ message: "Le paramètre doit être un nombre" });
    }
});

app.listen("3000", () => {
    console.log("http://127.0.0.1:3000");
});
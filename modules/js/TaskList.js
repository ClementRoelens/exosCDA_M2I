import { Task } from "./Task.js";

export class TaskList {
    constructor() {
        this.tasks = [];
        this.nbTasks = 0;
    }

    addTask(text) {
        const id = this.nbTasks++;
        const task = new Task(id, text);
        this.tasks.push(task);
    }

    removeTask(id) {
        this.tasks = this.tasks.filter(task => task.id != id);
        // let tab = []
        //     for(i = 0;i < this.tasks.length;i++){
        //             if(this.tasks[i].id !== id){
        //                     tab.push(this.tasks[i])
        //             }
        //     })

        // this.tasks = tab
    }

    renderTasks() {
        const taskList = document.getElementById('taskList');
        taskList.innerHTML = ''; // Effacez la liste actuelle

        this.tasks.forEach(task => {
            const taskItem = task.render();
            taskList.appendChild(taskItem);
        });
        
    }
}

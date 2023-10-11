export class Task {


    constructor(private _id: number = 0, private _name: string = "Rien", private _description: string = "Ceci est une tâche vide", private _deadline: Date = new Date(), private _completed: boolean = false) {
    }

    completeTask(): void {
        this._completed = true;
    }

    get id(): number {
        return this._id;
    }

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get description(): string {
        return this._description;
    }

    set description(value: string) {
        this._description = value;
    }

    get deadline(): Date {
        return this._deadline;
    }

    set deadline(value: Date) {
        this._deadline = value;
    }

    get completed(): boolean {
        return this._completed;
    }
}
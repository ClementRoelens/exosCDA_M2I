export class Task {
    

    constructor(private _id:number, private _name: string, private _deadline: Date,private _completed: boolean) {
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
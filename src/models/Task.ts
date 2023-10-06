export class Task {
    private _id:number;
    private static _count:number = 0;
    private _completed:boolean;

    constructor (private _name:string,private _deadline:Date){
        this._id = Task._count++;
        this._completed = false;
    }

    get id(): number {
        return this._id;
    }

    get name(): string {
        return this._name;
    }

    get deadline(): Date {
        return this._deadline;
    }

    get completed(): boolean {
        return this._completed;
    }

    completeTask(){
        this._completed = true;
    }
}
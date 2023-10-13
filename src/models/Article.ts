export class Article {
    constructor(private _id: number, private _name: string, private _hardware:string, private _imagePath:string, private _price:number) {
    }

    get id(): number {
        return this._id;
    }
    set id(value: number) {
        this._id = value;
    }
    get name(): string {
        return this._name;
    }
    set name(value: string) {
        this._name = value;
    }
    get hardware(): string {
        return this._hardware;
    }
    set hardware(value: string) {
        this._hardware = value;
    }
    get imagePath(): string {
        return this._imagePath;
    }
    set imagePath(value: string) {
        this._imagePath = value;
    }
    get price():number{
        return this._price;
    }
    set price(value:number){
        this._price = value;
    }
}
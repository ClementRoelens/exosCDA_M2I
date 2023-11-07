import uuid from 'react-native-uuid';

export class Article{
    private _id:string;

    constructor (private _name:string, private _price:number, private _quantity:number){
        this._id = uuid.v4().toString();
    }

    get id() : string {
        return this._id;
    }

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get price(): number {
        return this._price;
    }

    set price(value: number) {
        this._price = value;
    }

    get quantity(): number {
        return this._quantity;
    }

    set quantity(value: number) {
        this._quantity = value;
    }
}
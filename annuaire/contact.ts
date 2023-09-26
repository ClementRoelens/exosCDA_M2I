export class Contact {
    private _firstname: string;
    private _lastname: string;
    private _birthday: Date;
    private _email: string;
    private _phone: string;
    private _imageUrl: string;
    private _id: number;
    private static _count: number = 0;

    constructor(firstname: string, lastname: string, birthday: Date, email: string, phone: string, imageUrl: string) {
        this._firstname = firstname;
        this._lastname = lastname;
        this._birthday = birthday;
        this._email = email;
        this._phone = phone;
        this._imageUrl = imageUrl;
        this._id = Contact._count++;
    }

    get firstname(): string {
        return this._firstname;
    }

    get lastname(): string {
        return this._lastname;
    }

    get birthday(): Date {
        return this._birthday;
    }

    get email(): string {
        return this._email;
    }

    get phone(): string {
        return this._phone;
    }

    get imageUrl(): string {
        return this._imageUrl;
    }

    get id(): number {
        return this._id;
    }

    set firstname(value:string) {
        this._firstname = value;
    }

    set lastname(value: string) {
        this._lastname = value;
    }
    
    set birthday(value: Date) {
        this._birthday = value;
    }
    
    set email(value: string) {
        this._email = value;
    }
    
    set phone(value: string) {
        this._phone = value;
    }
    
    set imageUrl(value: string) {
        this._imageUrl = value;
    }

    getFullname(): string {
        return this._firstname + " " + this._lastname;
    }

    getAge(): number {
        const today = new Date();
        let age = today.getFullYear() - this._birthday.getFullYear();
        if (today.getMonth() > this._birthday.getMonth()
            ||
            (today.getMonth() === this._birthday.getMonth() && today.getDay() >= this._birthday.getDay())) {
            return age;
        }
        return --age;
    }
}
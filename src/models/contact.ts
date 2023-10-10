export class Contact {
    private _id: number;
    private static count: number = 0;

    constructor(
        private _firstname: string = "John",
        private _lastname: string = "Doe",
        private _email: string = "john.doe@nothing.com",
        private _phonenumber: number = 1234567890) {
            this._id = Contact.count++;
    }

    get id(): number {
        return this._id;
    }

    get firstname(): string {
        return this._firstname;
    }

    set firstname(value: string) {
        this._firstname = value;
    }

    get lastname(): string {
        return this._lastname;
    }

    set lastname(value: string) {
        this._lastname = value;
    }

    get email(): string {
        return this._email;
    }

    set email(value: string) {
        this._email = value;
    }

    get phonenumber(): number {
        return this._phonenumber;
    }

    set phonenumber(value: number) {
        this._phonenumber = value;
    }
}
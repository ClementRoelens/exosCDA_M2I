export class Contact {
    constructor(firstname, lastname, birthday, email, phone, imageUrl) {
        this._firstname = firstname;
        this._lastname = lastname;
        this._birthday = birthday;
        this._email = email;
        this._phone = phone;
        this._imageUrl = imageUrl;
        this._id = Contact._count++;
    }
    get firstname() {
        return this._firstname;
    }
    get lastname() {
        return this._lastname;
    }
    get birthday() {
        return this._birthday;
    }
    get email() {
        return this._email;
    }
    get phone() {
        return this._phone;
    }
    get imageUrl() {
        return this._imageUrl;
    }
    get id() {
        return this._id;
    }
    set firstname(value) {
        this._firstname = value;
    }
    set lastname(value) {
        this._lastname = value;
    }
    set birthday(value) {
        this._birthday = value;
    }
    set email(value) {
        this._email = value;
    }
    set phone(value) {
        this._phone = value;
    }
    set imageUrl(value) {
        this._imageUrl = value;
    }
    getFullname() {
        return this._firstname + " " + this._lastname;
    }
    getAge() {
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
Contact._count = 0;

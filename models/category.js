export class Category {
    #id;
    #title;
    #color;

    constructor(id, title, color) {
        this.#id = id;
        this.#title = title;
        this.#color = color;
    }

    get id() {
        return this.#id;
    }

    get title() {
        return this.#title;
    }

    get color() {
        return this.#color;
    }
}



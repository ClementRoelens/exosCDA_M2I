export class Film {
    private _id: number;
    private static _count = 0;

    constructor(private _title: string, private _director: string, private _releaseDate: Date, private _posterPath: string) {
        this._id = Film._count++;
    }

    get id(): number {
        return this._id;
    }
    
    get title(): string {
        return this._title;
    }

    set title(value: string) {
        this._title = value;
    }

    get director(): string {
        return this._director;
    }

    set director(value: string) {
        this.director = value;
    }

    get releaseDate(): Date {
        return this._releaseDate;
    }

    set releaseDate(value: Date) {
        this._releaseDate = value;
    }

    get posterPath(): string {
        return this._posterPath;
    }

    set posterPath(value: string) {
        this._posterPath = value;
    }
}
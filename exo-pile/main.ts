class Pile<T> {
    private _array:T[];

    constructor(){
        this._array = [];
    }

    get array() : T[]{
        return this._array;
    }

    Acceder(index:number):T{
        return this._array[index];
    }

    Empiler(value:T){
        this._array.push(value);
    }

    Depiler(){
        this._array.pop();
    }

    Compter() : number {
        return this._array.length;
    }
}

const nombres:Pile<number> = new Pile();
nombres.Empiler(42);
nombres.Empiler(666);
nombres.Empiler(32);
nombres.Empiler(-4658787482783);

console.log("La pile contient :");
console.table(nombres.array);
console.log(`Soit ${nombres.Compter()} nombres`);
console.log("Le deuxième nombre est " + nombres.Acceder(1));
console.log("On ajoute 64");
nombres.Empiler(64);
console.log("On le voit maintenant dans la pile");
console.table(nombres.array);
console.log("Et on l'enlève");
nombres.Depiler();
console.table(nombres.array);

const mots:Pile<string> = new Pile();
mots.Empiler("Hello");
mots.Empiler("darkness");
mots.Empiler("my");
mots.Empiler("old");
mots.Empiler("friend");
console.log("Et on peut faire pareil avec des mots");
console.table(mots.array);
mots.Empiler("geh5thrt8hrt4");
console.table(mots.array);
mots.Depiler();
console.table(mots.array);

console.log("On peut même le faire avec des tableaux lol");
const chiant:Pile<string[]> = new Pile();
chiant.Empiler(["Hello","darkness","my","old","friend"]);
chiant.Empiler(["I've","come","to","talk","with","you","again"]);
console.table(chiant.array);
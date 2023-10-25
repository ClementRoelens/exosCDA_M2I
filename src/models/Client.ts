export interface Client {
    id:string;
    firstname:string;
    lastname:string;
    // Vu qu'on peut pas faire d'opértion dessus et qu'on doit afficher le 0 ou le +33, mieux un string qu'un number ?
    phoneNumber:string;
}
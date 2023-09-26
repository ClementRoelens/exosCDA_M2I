import { Admin } from "./admin.interface";
import { User } from "./user.interface";

type Personne = User | Admin;

const personnes:Personne[] = [
    {
        nom:"Toto Dupont",
        age:35,
        occupation:"Facteur"
    },
    {
        nom : "Jeanne Doe",
        age:25,
        role:"Admin"
    },
    {
        nom:"Michel Forever",
        age:23,
        occupation:"Lutteur"
    },
    {
        nom:"Michael Flinch",
        age:64,
        role:"Gérant"
    }
];

personnes.forEach((personne:Personne) => {
    console.log(personne.nom + " a " + personne.age + " ans.");
});
const personnes = [
    {
        nom: "Toto Dupont",
        age: 35,
        occupation: "facteur"
    },
    {
        nom: "Jeanne Doe",
        age: 25,
        role: "admin"
    },
    {
        nom: "Michel Forever",
        age: 23,
        occupation: "lutteur"
    },
    {
        nom: "Michael Flinch",
        age: 64,
        role: "gérant"
    }
];
personnes.forEach((personne) => {
    let message = personne.nom + " a " + personne.age + " ans. ";
    message += ('occupation' in personne) ? "Son occupation, c'est " + personne.occupation : "Son rôle, c'est " + personne.role;
    console.log(message);
});
export {};

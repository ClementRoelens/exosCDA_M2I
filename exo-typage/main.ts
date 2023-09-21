// 1
const userName: string = "Clément";

// 2
const userAge: number = 32;

// 3
const isLogin: boolean = true;

// 4
const userNames: string[] = [];
userNames.push(userName);
console.log(userNames);

// 5
const person: object = {
    firstName: "Dupont",
    age: 20,
    isLoggedIn: true
};
// console.log(person.age); 
// Property 'age' does not exist on type 'object'
// Puisque le type "object" ne possède aucun attribut age

// 6
const person1: { firstName: string, age: number, isLoggedIn: boolean } = {
    firstName: "Dupont",
    age: 20,
    isLoggedIn: true
};
console.log(person1.age); 

// 7
const person2 = {
    firstName: "Dupont",
    age: 20,
    isLoggedIn: true
};
console.log(person2.age);

// 8
const infos: [string, number] = ["Clément", 32];
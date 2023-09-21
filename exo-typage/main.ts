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
let person: object;
person = {
    firstName: "Dupont",
    age: 20,
    isLoggedIn: true
};
console.log(person.age); // Property 'age' does not exist on type 'object'

// 6
const person1: object = {
    firstName: "Dupont",
    age: 20,
    isLoggedIn: true
};
console.log(person1.age); // Property 'age' does not exist on type 'object'

// 7
const person2 = {
    firstName: "Dupont",
    age: 20,
    isLoggedIn: true
};
console.log(person2.age);

// 8
const infos: [string, number] = ["Clément", 32];
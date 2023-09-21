// 1
var userName = "Clément";
// 2
var userAge = 32;
// 3
var isLogin = true;
// 4
var userNames = [];
userNames.push(userName);
console.log(userNames);
// 5
var person = {
    firstName: "Dupont",
    age: 20,
    isLoggedIn: true
};
// console.log(person.age); 
// Property 'age' does not exist on type 'object'
// Puisque le type "object" ne possède aucun attribut age
// 6
var person1 = {
    firstName: "Dupont",
    age: 20,
    isLoggedIn: true
};
console.log(person1.age);
// 7
var person2 = {
    firstName: "Dupont",
    age: 20,
    isLoggedIn: true
};
console.log(person2.age);
// 8
var infos = ["Clément", 32];

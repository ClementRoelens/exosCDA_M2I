import { Parking } from "./classes/parking.js";
import { Vehicule } from "./classes/vehicule.js";

function feedback(type, message) {
    let feedback;
    switch (type) {
        case "success":
            feedback = document.getElementById("feedbackSuccess");
            break;
        case "fail":
            feedback = document.getElementById("feedbackFail");
            break;
        case "pay":
            feedback = document.getElementById("feedbackPay");
            break;
    }
    feedback.innerHTML = message;
    feedback.style.display = "block";
    setTimeout(() => {
        feedback.style.display = "none";
    }, 5000)
}

const parking = new Parking();


// Création de véhicules et simulation de parkings plus vieux

parking.startSession(new Vehicule("367-LM-52"));
parking.startSession(new Vehicule("152-HU-52"));
parking.startSession(new Vehicule("455-IF-52"));
parking.startSession(new Vehicule("362-NJ-52"));

parking.sessions[0].startSessionDateTime = Date.now() - 3600000;
parking.sessions[1].startSessionDateTime = Date.now() - (30 * 60000);
parking.sessions[2].startSessionDateTime = Date.now() - (5 * 60000);
parking.sessions[3].startSessionDateTime = Date.now() - (25 * 60000);



const start = document.getElementById("startSession");
const end = document.getElementById("endSession");

start.addEventListener('click', e => {
    e.preventDefault();
    const id = document.querySelector("input").value;
    if (id !== "") {
        if (parking.startSession(new Vehicule(id))) {
            feedback("success", `Veuillez prendre votre ticket pour le véhicule ${id}`);
        } else {
            feedback("fail", `Le véhicule ${id} est déjà dans le parking`);
        }
    } else {
        alert("Entrez une immatriculation au lieu de troller");
    }
});

end.addEventListener('click', e => {
    e.preventDefault();
    const id = document.querySelector("input").value;
    if (id !== "") {
        const price = parking.endSession(id);
        if (price !== -1) {
            feedback("pay",`Le prix à payer pour le véhicule ${id} est de ${price}€`);
        } else {
            feedback("fail", `Le véhicule ${id} n'existe pas`);
        }
    } else {
        alert("Entrez une immatriculation au lieu de troller");
    }
});
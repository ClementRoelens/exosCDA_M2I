import Pokemon from "./pokemon.js";

function findPokemon(index = Math.floor(Math.random() * 150) + 1) {
    fetch(apiUrl + index)
        .then(res => {
            if (!res.ok) {
                throw new Error(`Quelque chose s'est mal passé. Code d'erreur : ${res.status}`);
            }
            return res.json();
        })
        .then(pokemon => {
            currentPokemon = new Pokemon(
                pokemon.name,
                pokemon.id,
                Math.floor(pokemon.weight * 0.453592 * 100) / 100,
                Math.floor(pokemon.height * 0.3048 * 100) / 100,
                pokemon.sprites.front_default);
            insertPokemon();
            eraseBtn.style.display = 
            pokemonTeam.some(pokemon => pokemon.id == currentPokemon.id) ? 
            "inline" :
            "none";
        })
        .catch(error => console.log(error));
}

function insertPokemon() {
    currentPokemonImg.src = currentPokemon.imageUrl;
    pokemonName.innerHTML = currentPokemon.name;
    weightAndHeight.innerHTML = `${currentPokemon.height}m pour ${currentPokemon.weight}kg`;
    pokemonIndex.innerHTML = `#${currentPokemon.id}`;
}

function previousPokemon() {
    currentIndex = (currentIndex - 1 < 1) ? 151 : --currentIndex;
    findPokemon(currentIndex);
}

function nextPokemon() {
    currentIndex = (currentIndex + 1 > 151) ? 1 : ++currentIndex;
    findPokemon(currentIndex);
}

function addPokemonToTeam() {
    if (pokemonTeam.length < 6) {
        if (pokemonTeam.some(pokemon => pokemon.id == currentPokemon.id)) {
            warn("Tu as déjà ce Pokémon dans ton équipe");
        } else {
            pokemonTeam.push(currentPokemon);
            const img = document.createElement('img');
            img.setAttribute('data-id', currentPokemon.id);
            img.classList.add('team-member', 'rounded-circle', 'mx-auto', 'bg-danger', 'bg-opacity-75', 'p-3');
            img.src = currentPokemon.imageUrl;
            img.addEventListener('click',e => {
                findPokemon(e.target.dataset.id);
            })
            teamElement.appendChild(img);
            eraseBtn.style.display = "inline";
        }
    } else {
        warn("Tu ne peux pas avoir plus de 6 Pokémon dans ton équipe !");
    }
}

function warn(message) {
    const warnP = document.querySelector('#warn-message');
    warnP.style.opacity = 1;
    warnP.textContent = message;
    setTimeout(() => {
        warnP.style.opacity = 0;
        setTimeout(() => {
            warnP.textContent = "";
        }, 500);
    }, 2000);
}

function showTooltip(e) {
    currentPokemonImg.removeEventListener('mouseenter', showTooltip);
    const tooltip = document.createElement('p');
    tooltip.textContent = "Clique-moi dessus pour m'ajouter à ton équipe !";
    tooltip.classList.add('personnal-tooltip');
    tooltip.style.top = e.clientY + "px";
    tooltip.style.left = e.clientX + "px";
    tooltip.style.display = 'block';
    document.querySelector('main').appendChild(tooltip);
    setTimeout(() => {
        tooltip.style.opacity = 0;
        setTimeout(() => {
            tooltip.parentElement.removeChild(tooltip);
        }, 2000)
    }, 3000);
}

function erasePokemon(e){
    const teamMembers = document.querySelectorAll('.team-member');
    for (const element of teamMembers){
        if (element.dataset.id == currentPokemon.id){
            teamElement.removeChild(element);
            break;
        }
    }
    pokemonTeam = pokemonTeam.filter(pokemon => pokemon.id != currentPokemon.id);
    eraseBtn.style.display = "none";
}

const apiUrl = "https://pokeapi.co/api/v2/pokemon/";
let currentIndex = Math.floor(Math.random() * 150) + 1;
let currentPokemon;
let pokemonTeam = [];

const previousArrow = document.querySelector('.bi-caret-left');
const nextArrow = document.querySelector('.bi-caret-right');
const currentPokemonImg = document.querySelector('#current-pokemon');
const pokemonName = document.querySelector('h2');
const pokemonIndex = document.querySelector('h4');
const weightAndHeight = document.querySelector('#weightAndHeight');
const surprise = document.querySelector('button');
const eraseBtn = document.querySelector('#erase-pokemon');
const teamElement = document.querySelector('#team');

previousArrow.addEventListener('click', previousPokemon);
nextArrow.addEventListener('click', nextPokemon);
window.addEventListener('keydown', e => {
    if (e.key === 'ArrowRight') {
        nextPokemon();
    } else if (e.key === 'ArrowLeft') {
        previousPokemon();
    }
});
surprise.addEventListener('click', () => {
    findPokemon();
});
findPokemon(currentIndex);

currentPokemonImg.addEventListener('mouseenter', showTooltip);
currentPokemonImg.addEventListener('click', addPokemonToTeam);
eraseBtn.addEventListener('click',erasePokemon);

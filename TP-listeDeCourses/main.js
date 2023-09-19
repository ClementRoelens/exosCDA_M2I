function createListItem(value,id) {
    const li = document.createElement('li');
    const p = document.createElement('p');
    const div = document.createElement('div');
    const trash = document.createElement('i');
    const pencilSquare = document.createElement('i');
    
    p.textContent = value;
    p.classList.add('ms-0');
    li.classList.add('list-group-item', 'd-flex', 'justify-content-between');
    li.setAttribute('data-id',id);
    div.classList.add('me-3');
    pencilSquare.classList.add('bi', 'bi-pencil-square', 'text-success', 'btn');
    pencilSquare.setAttribute("data-id",id);
    trash.classList.add('bi', 'bi-trash', 'text-danger', 'btn');
    trash.setAttribute("data-id",id);
    
    pencilSquare.addEventListener('click',e=>{
        editItem(e.target.dataset.id);
    });
    trash.addEventListener('click',e => {
        eraseItem(e.target.dataset.id);
    });
    
    div.appendChild(pencilSquare);
    div.appendChild(trash);
    
    li.appendChild(p);
    li.appendChild(div);
    
    ul.appendChild(li);
}

function editItem(value,id){
    const index = findItem(id);
    const li = document.querySelectorAll('li');
    // shoppingList[index].name = value;
    // localStorage.setItem('shoppingList', JSON.stringify(shoppingList));
    // renderList();
}

function eraseItem(id){
    shoppingList.splice(findItem(id),1);
    localStorage.setItem('shoppingList', JSON.stringify(shoppingList));
    renderList();
}

function findItem(id){
    const shoppingListIds = shoppingList.map(item => item.id);
    return shoppingListIds.indexOf(Number(id));
}

function addElementToList(){
    if (input.value !== "") {
        const shoppingElement = {
            id : count,
            name : input.value
        };
        shoppingList.push(shoppingElement);
        localStorage.setItem('shoppingList', JSON.stringify(shoppingList));
        
        createListItem(input.value,count);
        
        localStorage.setItem('count',++count);
        input.value = "";
    }
}

function renderList(){
    ul.innerHTML = '';
    shoppingList.forEach(storedElement => {
        createListItem(storedElement.name,storedElement.id);
    })
}


const ul = document.querySelector('ul');
let shoppingList = [];
let count = 0;
window.onload = () => {
    if (localStorage.getItem('shoppingList')) {
        shoppingList = JSON.parse(localStorage.getItem('shoppingList'));
        renderList();
    }
    if (localStorage.getItem('count')){
        count = Number(localStorage.getItem('count'));
    }
}

const add = document.querySelector('button');
const input = document.querySelector('input');

add.addEventListener('click', addElementToList);
input.addEventListener('keydown',e=>{
    if (e.key === "Enter"){
        addElementToList();
    }
});
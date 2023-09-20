function createListItem(value, id) {
    const li = document.createElement('li');
    const p = document.createElement('p');
    const div = document.createElement('div');
    const trash = document.createElement('i');
    const pencilSquare = document.createElement('i');

    p.textContent = value;
    p.classList.add('ms-0');
    li.classList.add('list-group-item', 'd-flex', 'justify-content-between');
    li.setAttribute('data-id', id);
    div.classList.add('me-3');
    pencilSquare.classList.add('bi', 'bi-pencil-square', 'text-success', 'btn');
    pencilSquare.setAttribute("data-id", id);
    trash.classList.add('bi', 'bi-trash', 'text-danger', 'btn');
    trash.setAttribute("data-id", id);

    pencilSquare.addEventListener('click', e => {
        editItem(e.target.dataset.id, id);
    });
    trash.addEventListener('click', e => {
        eraseItem(e.target.dataset.id);
    });

    div.appendChild(pencilSquare);
    div.appendChild(trash);

    li.appendChild(p);
    li.appendChild(div);

    ul.appendChild(li);
}

function editItem(id) {
    const index = findItem(id);
    const li = document.querySelectorAll('li');
    for (const liElement of li) {
        if (liElement.dataset.id == id) {
            liElement.classList.add('display-6');
            editMode = true;
            editIndex = index;
            input.value = shoppingList[index].name;
            break;
        }
    }
}

function eraseItem(id) {
    shoppingList.splice(findItem(id), 1);
    localStorage.setItem('shoppingList', JSON.stringify(shoppingList));
    renderList();
}

function findItem(id) {
    const shoppingListIds = shoppingList.map(item => item.id);
    return shoppingListIds.indexOf(Number(id));
}

function addElementToList() {
    if (input.value !== "") {
        const id = Date.now();
        const shoppingElement = {
            id: id,
            name: input.value
        };
        if (!editMode) {
            shoppingList.push(shoppingElement);
            createListItem(input.value, id);
        } else {
            shoppingList[editIndex].name = input.value;
            editMode = false;
            renderList();
        }
        localStorage.setItem('shoppingList', JSON.stringify(shoppingList));
        input.value = "";
    }
}

function renderList() {
    ul.innerHTML = '';
    shoppingList.forEach(storedElement => {
        createListItem(storedElement.name, storedElement.id);
    })
}


const ul = document.querySelector('ul');
let shoppingList = [];
let editMode = false;
let editIndex = 0;
window.onload = () => {
    if (localStorage.getItem('shoppingList')) {
        shoppingList = JSON.parse(localStorage.getItem('shoppingList'));
        renderList();
    }
}

const add = document.querySelector('button');
const input = document.querySelector('input');

add.addEventListener('click', addElementToList);
input.addEventListener('keydown', e => {
    if (e.key === "Enter") {
        addElementToList();
    }
});
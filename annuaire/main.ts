import { Contact } from "./contact.js";

function checkInputsBeforeValidate() {
    // Puisqu'il n'est pas possible d'empêcher le comportement par défaut du button de fermeture de la modale
    // j'active le bouton seulement si aucun champ n'est vide. Sinon, il est disabled
    const inputsModal = document.querySelectorAll('.modal-body input') as NodeListOf<HTMLInputElement>;
    inputsModal.forEach((input: HTMLInputElement) => {
        input.addEventListener('change', () => {
            let flag: boolean = false;
            for (const checkedInput of inputsModal) {
                if (checkedInput.value === "") {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                addContactButton.disabled = false;
            }
        });
    })
}

function renderContacts(): void {
    contacts.forEach((contact: Contact) => {
        addButtonContact(contact);
    })
    displayContact(selectedContact);
    contactsElement.children[0].classList.remove('btn-outline-light');
    contactsElement.children[0].classList.add('btn-light', 'text-dark');
}

function addButtonContact(contact: Contact) {
    // On crée les boutons avec le nom complet du contact, et en passant son id en dataset
    const button: HTMLButtonElement = document.createElement("button");
    button.classList.add("btn", "btn-outline-light", "my-1");
    button.setAttribute("data-id", contact.id.toString());
    button.textContent = contact.getFullname();
    // Puis on ajoute sur chaque bouton un eventListener qui va afficher le contact au clic, en se servant de l'id
    button.addEventListener("click", (e: MouseEvent) => {
        const element = e.target as HTMLButtonElement;
        const id: number = Number(element.dataset.id);
        // Il faut renoircir le bouton du contact qui était jusqu'ici sélectionné
        const previouslySelectedButton = findButton(selectedContact.id) as HTMLButtonElement;
        previouslySelectedButton.classList.remove('btn-light', 'text-dark');
        previouslySelectedButton.classList.add('btn-outline-light');
        const newSelectedContact = contacts.find(seekedContact => seekedContact.id === id) as Contact;
        displayContact(newSelectedContact);
        // Et on blanchit le bouton vu qu'il devient le contact sélectionné
        element.classList.add('btn-light', 'text-dark');
    });
    contactsElement.appendChild(button);
}

function displayContact(contact: Contact): void {
    firstname.value = contact.firstname;
    lastname.value = contact.lastname;
    birthday.value = contact.birthday.toLocaleDateString();
    age.textContent = contact.getAge().toString() + " ans";
    email.value = contact.email;
    phone.value = contact.phone;
    image.src = contact.imageUrl;
    image.alt = contact.getFullname();

    selectedContact = contact;
}

function addContact(e: MouseEvent) {
    e.preventDefault();
    const firstnameModal = document.querySelector('#firstnameModal') as HTMLInputElement;
    const lastnameModal = document.querySelector('#lastnameModal') as HTMLInputElement;
    const birthdayModal = document.querySelector('#birthdayModal') as HTMLInputElement;
    const emailModal = document.querySelector('#emailModal') as HTMLInputElement;
    const phoneModal = document.querySelector('#phoneModal') as HTMLInputElement;
    const avatarURLModal = document.querySelector('#avatarURL') as HTMLInputElement;

    const newContact = new Contact(firstnameModal.value, lastnameModal.value, new Date(birthdayModal.value), emailModal.value, phoneModal.value, avatarURLModal.value);
    contacts.push(newContact);
    // On enregistre notre tableau
    localStorage.setItem('contacts', JSON.stringify(contacts));
    addButtonContact(newContact);
    displayContact(newContact);

    // Apparemment, Bootstrap ne vide pas les champs par lui-même, donc on utilise form.reset() (merci Rémi)
    const form = document.querySelector('.modal-body') as HTMLFormElement;
    form.reset();
    // Et on repasse le button en disabled pour empêcher d'ajouter un contact vide
    addContactButton.disabled = true;
}

function launchEditMode(e: MouseEvent) {
    e.preventDefault();
    // En mode édition, tous les inputs perdent le readonly
    const inputs = document.querySelectorAll('.infos input') as NodeListOf<HTMLInputElement>;
    inputs.forEach((input: HTMLInputElement) => {
        input.readOnly = false;
    });
    birthday.type = "date";
    // Il faut donner une string formaté spécifiquement au input[type="date"]...
    const year: string = selectedContact.birthday.getFullYear().toString();
    // On utilise padStart pour que 2 donne 02
    const month: string = selectedContact.birthday.getMonth() >= 10 ?
        (selectedContact.birthday.getMonth() + 1).toString() :
        (selectedContact.birthday.getMonth() + 1).toString().padStart(2, '0');
    const day: string = selectedContact.birthday.getDate() >= 10 ?
        selectedContact.birthday.getDate().toString() :
        selectedContact.birthday.getDate().toString().padStart(2, '0');
    birthday.value = `${year}-${month}-${day}`;
    // On change le bouton Editer en un bouton Valider (et on rechangera quand on sortira du mode édition)
    editButtonText.innerHTML = "Valider";
    // Et du coup, on change la fonction lancée au clic
    editContactButton.removeEventListener('click', launchEditMode);
    editContactButton.addEventListener('click', editContact);
    // On crée une nouvelle ligne dans le formulaire, contenant l'URL de notre image
    const avatarDiv: HTMLDivElement = document.createElement('div');
    avatarDiv.id = "avatarEditDiv";
    const label: HTMLLabelElement = document.createElement('label');
    const input: HTMLInputElement = document.createElement('input');
    label.classList.add("form-label", "text-light", "mt-3");
    label.setAttribute("for", "avatarEditURL")
    label.textContent = "URL de votre avatar :"
    input.classList.add("form-control");
    input.id = "avatarEditURL";
    input.placeholder = "URL de votre avatar";
    input.value = selectedContact.imageUrl;
    input.required = true;
    avatarDiv.appendChild(label);
    avatarDiv.appendChild(input);
    const hr = document.querySelector(".infos hr") as HTMLHRElement;

    infos.insertBefore(avatarDiv, hr);
}

function editContact(e: MouseEvent) {
    e.preventDefault();
    // On va lister les champs laissés vides dans notre formulaire
    const inputs = document.querySelectorAll('.infos input') as NodeListOf<HTMLInputElement>;
    let emptyInputs: HTMLInputElement[] = [];
    inputs.forEach((input: HTMLInputElement) => {
        if (input.value === "") {
            emptyInputs.push(input);
        }
    });
    // Si au moins un champ est vide, on ne modifie pas le contact et on met en évidence ces champs
    if (emptyInputs.length > 0) {
        emptyInputs.forEach((input: HTMLInputElement) => {
            input.classList.add("border-danger");
            input.placeholder += " requis";
        })
        // Sinon, on modifie le contact
    } else {
        selectedContact.firstname = firstname.value;
        selectedContact.lastname = lastname.value;
        selectedContact.birthday = new Date(birthday.value);
        selectedContact.email = email.value;
        selectedContact.phone = phone.value;
        // On enregistre notre modification
        localStorage.setItem('contacts', JSON.stringify(contacts));
        // On supprime la mise en évidence
        inputs.forEach((input: HTMLInputElement) => {
            input.classList.remove("border-danger");
            input.readOnly = true;
        });
        // On uploade notre DOM
        const buttonToChange = findButton(selectedContact.id) as HTMLButtonElement;
        buttonToChange.innerHTML = selectedContact.getFullname();
        // Et on rechange les événements des boutons et on supprime la ligne de l'avatar URL
        editContactButton.removeEventListener('click', editContact);
        editContactButton.addEventListener('click', launchEditMode);
        const avatarDiv = document.querySelector('#avatarEditDiv') as HTMLDivElement;
        const parentAvatarDiv = avatarDiv.parentNode as HTMLElement;
        parentAvatarDiv.removeChild(avatarDiv);
        editButtonText.innerHTML = "Modifier";
    }
}

function findButton(id: number): HTMLButtonElement | undefined {
    for (let i: number = 0; i < contactsElement.children.length; i++) {
        const contactElement = contactsElement.children[i] as HTMLButtonElement;
        if (contactElement.dataset.id === id.toString()) {
            return contactElement;
        }
    }
}

function removeContact(e: MouseEvent) {
    e.preventDefault();
    // On supprime le contact du tableau
    const index = contacts.indexOf(selectedContact);
    contacts.splice(index, 1);
    // On enregistre notre modification
    localStorage.setItem('contacts', JSON.stringify(contacts));
    // On va aussi supprimer le bouton correspondant (grâce au dataset)
    const removedButton = document.querySelector(`button[data-id='${selectedContact.id}'`) as HTMLButtonElement;
    const parent = removedButton.parentNode as HTMLElement;
    parent.removeChild(removedButton);
    // On affiche un autre contact
    if (contacts.length > 0) {
        const displayedContact = (index >= 0 && index < contacts.length) ? contacts[index] : contacts[0];
        displayContact(displayedContact)
        // Sauf s'il n'y en a plus, là on affiche le contact anonyme par défaut
    } else {
        displayContact(anonymousContact);
    }
}

const storedContacts: string | null = localStorage.getItem('contacts');
let contacts: Contact[] = [];
// S'il y a une liste de contacts enregistrée en localStorage, on l'assigne à contacts. Sinon, on les crée
if (storedContacts) {
    // Sans cette manip bizarre, mon navigateur ne veut pas considérer mes éléments comme des contacts
    // (et en faisant des new Contact on se met à niveau avec l'id)
    contacts = JSON.parse(storedContacts).map((contact:any) => new Contact(
        contact._firstname,
        contact._lastname,
        new Date(contact._birthday),
        contact._email,
        contact._phone,
        contact._imageUrl
    ));
} else {
    contacts = [
        new Contact("Albert", "Einstein", new Date(1879, 2, 14), "godDoestNotPlayDice@relativity.com", "+33119788254", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/Albert_Einstein_1947.jpg/800px-Albert_Einstein_1947.jpg"),
        new Contact("Stanley", "Milgram", new Date(1933, 7, 15), "submit@authority.com", "+33119788254", "https://upload.wikimedia.org/wikipedia/commons/7/77/Stanley_Milgram.jpg"),
        new Contact("Charles", "Darwin", new Date(1809, 1, 12), "adaptOrDie@fitness.com", "+33119788254", "https://upload.wikimedia.org/wikipedia/commons/f/f1/Charles_Darwin_portrait.jpg"),
        new Contact("Jeffrey", "Atkins", new Date(1976, 1, 29), "notAScientist.justBornA29thFebruary@lol.com", "+33119788254", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Ja_Rule_in_2016.jpg/800px-Ja_Rule_in_2016.jpg")
    ];
}
// Ce contact anonyme sera affiché si tous les contacts sont supprimés
const anonymousContact = new Contact("John", "Doe", new Date(1970, 0, 1), "ajouteUnContact@silteplait.com", "+33119788254", "./assets/unknown.jpg");
let selectedContact: Contact = contacts[0];
// On sélectionne tous les éléments qu'on va manipuler
const contactsElement = document.querySelector(".contacts") as HTMLDivElement;
const firstname = document.querySelector('#firstname') as HTMLInputElement;
const lastname = document.querySelector('#lastname') as HTMLInputElement;
const birthday = document.querySelector('#birthday') as HTMLInputElement;
const age = document.querySelector('.age') as HTMLSpanElement;
const email = document.querySelector('#email') as HTMLInputElement;
const phone = document.querySelector('#phone') as HTMLInputElement;
const image = document.querySelector('#contactImage') as HTMLImageElement;
const addContactButton = document.querySelector('#addContact') as HTMLButtonElement;
const editContactButton = document.querySelector('#editContact') as HTMLButtonElement;
const removeContactButton = document.querySelector('#removeContact') as HTMLButtonElement;
const infos = document.querySelector(".infos") as HTMLDivElement;
const editButtonText = document.querySelector("#editButtonText") as HTMLSpanElement;

addContactButton.addEventListener('click', addContact);
checkInputsBeforeValidate();
editContactButton.addEventListener('click', launchEditMode);
removeContactButton.addEventListener('click', removeContact);

window.onload = renderContacts;


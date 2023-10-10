import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css'
import { ContactContext } from './context/ContactContext';
import { RouterProvider } from 'react-router-dom';
import { router } from './router/app-routing';
import { useState } from 'react';
import { Contact } from './models/contact';

function App() {
  const storedContacts = localStorage.getItem("contacts");
  let parsedContacts;
  let typedContacts:Contact[] = [];
  if (storedContacts){
    parsedContacts = JSON.parse(storedContacts) as Contact[];
    typedContacts = parsedContacts.map((contact:any) => new Contact(contact._firstname,contact._lastname,contact._email,contact._phonenumber));
  }
  const [contacts, setContacts] = useState<Contact[]>(typedContacts);

  return (
    <>
      <ContactContext.Provider value={{ contacts: contacts, setContacts: setContacts }}>
        <RouterProvider router={router} />
      </ContactContext.Provider>
    </>
  )
}

export default App

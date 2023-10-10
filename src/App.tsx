import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css'
import { ContactContext } from './context/ContactContext';
import { RouterProvider } from 'react-router-dom';
import { router } from './router/app-routing';
import { useState } from 'react';
import { Contact } from './models/contact';

function App() {
  const [contacts, setContacts] = useState<Contact[]>([]);

  return (
    <>
      <ContactContext.Provider value={{ contacts: contacts, setContacts: setContacts }}>
        <RouterProvider router={router} />
      </ContactContext.Provider>
    </>
  )
}

export default App

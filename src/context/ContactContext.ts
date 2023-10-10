import { createContext } from "react";
import { Contact } from "../models/contact";

export const ContactContext = createContext<ContactContext>({
    contacts: [],
    setContacts: () => { }
});

interface ContactContext {
    contacts: Contact[];
    setContacts: React.Dispatch<React.SetStateAction<Contact[]>>;
}
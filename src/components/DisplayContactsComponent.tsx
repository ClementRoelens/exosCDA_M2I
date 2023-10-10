import { useContext } from "react";
import { ContactContext } from "../context/ContactContext";
import { Contact } from "../models/contact";
import ContactComponent from "./ContactComponent";
import { useNavigate } from "react-router-dom";

function DisplayContactsComponent() {
    const { contacts } = useContext(ContactContext);
    const navigate = useNavigate()

    return (
        <>
        <div className="top-area d-flex justify-content-between">
            <h1>Liste de contacts</h1>
            <i className="btn bi bi-plus-circle text-success align-self-center" onClick={() => navigate("/contacts/create?mode=create")}></i>
        </div>
            <hr />
            <ul className="list-group">
                {contacts.map((contact: Contact, index: number) =>
                    <li key={index} className="list-group-item bg-dark text-light w-100 border border-info-subtle my-2 rounded">
                        <ContactComponent contact={contact} />
                    </li>
                )}
            </ul>
        </>
    );
}

export default DisplayContactsComponent;
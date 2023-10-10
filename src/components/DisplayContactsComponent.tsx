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
            <h1>Contact list</h1>
            <i className="btn bi bi-plus-circle text-success align-self-center" onClick={() => navigate("/create")}></i>
        </div>
            <hr />
            <ul className="list-group">
                {contacts.map((contact: Contact, index: number) =>
                    <li key={index} className="list-group-item">
                        <ContactComponent contact={contact} />
                    </li>
                )}
            </ul>
        </>
    );
}

export default DisplayContactsComponent;
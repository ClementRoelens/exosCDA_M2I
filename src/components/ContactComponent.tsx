import { useNavigate } from "react-router-dom";
import { Contact } from "../models/contact";

function ContactComponent(props: ContactProps) {
    const contact: Contact = props.contact;
    const navigate = useNavigate();

    return (
        <button className="btn border border-primary">
            <div className="contact-header">
                <h2>{contact.firstname} {contact.lastname}</h2>
                <div className="actions">
                    <button className="btn btn-outline-warning" onClick={() => navigate(`/contacts/${contact.id}?mode=edit`)}><i className="bi bi-pencil-square"></i> Edit</button>
                    <button className="btn btn-outline-danger" onClick={() => navigate(`/contacts/${contact.id}?mode=edlete`)}><i className="bi bi-trash"></i> Delete</button>
                </div>
            </div>
            <hr />
            <ul>
                <li><b>Email</b> : {contact.email}</li>
                <li><b>Phone number</b> : {contact.phonenumber}</li>
            </ul>
        </button>
    );
}

interface ContactProps {
    contact: Contact;
}

export default ContactComponent;
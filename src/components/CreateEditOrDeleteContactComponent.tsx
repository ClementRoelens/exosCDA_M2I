import { useNavigate, useParams, useSearchParams } from "react-router-dom";
import { Contact } from "../models/contact";
import { FormEvent, useContext, useEffect, useRef, useState } from "react";
import { ContactContext } from "../context/ContactContext";
import "bootstrap-icons/font/bootstrap-icons.min.css";

function CreateEditOrDeleteContactComponent() {
    const [searchParams] = useSearchParams();
    const mode = searchParams.get("mode");

    const params = useParams();
    const { contactId } = params;

    const [contact, setContact] = useState<Contact | null>(null);
    const [errorMessage, setErrorMessage] = useState<string>("");

    const { contacts, setContacts } = useContext(ContactContext);

    const firstnameRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const lastnameRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const emailRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const phoneRef = useRef() as React.MutableRefObject<HTMLInputElement>;

    const navigate = useNavigate();


    useEffect(() => {
        // Create, edit or delete
        if (mode !== "create"){
            if (contactId) {
                if (isNaN(Number(contactId))) {
                    // Implémenter ça après
                    
                    // const error: Error = { name: "Mauvaise URL", message: "Le paramètre que vous avez passé ne correspond pas à un nombre" };
                    // navigate("/error", { state: error });
                    setErrorMessage("Le paramètre que vous avez passé ne correspond pas à un nombre");
                } else {
                    const foundContact = contacts.find((contact: Contact) => contact.id === Number(contactId));
    
                    if (!foundContact) {
                        // Implémenter ça après
    
                        // const error: Error = { name: "Contact non-trouvé", message: "L'id passée ne correspond à aucun contact" };
                        // navigate("/error", { state: error });
                        setErrorMessage("L'id passée ne correspond à aucun contact");
                    } else {
                        setContact(foundContact);
                        firstnameRef.current.value = foundContact.firstname;
                        lastnameRef.current.value = foundContact.lastname;
                        emailRef.current.value = foundContact.email;
                        phoneRef.current.value = foundContact.phonenumber.toString();
                    }
                }
            }
        }
    }, []);

    function submitHandler(e: FormEvent) {
        e.preventDefault();
        switch (mode) {
            case "create":
                setContacts((prevContacts: Contact[]) => {
                    const newContacts = [...prevContacts, new Contact(firstnameRef.current.value, lastnameRef.current.value, emailRef.current.value, Number(phoneRef.current.value))];
                    localStorage.setItem("contacts",JSON.stringify(newContacts));
                    return newContacts;
                });
                break;
            case "edit":
                const newContact = new Contact(firstnameRef.current.value, lastnameRef.current.value, emailRef.current.value, Number(phoneRef.current.value));
                setContacts((prevContacts: Contact[]) => prevContacts.map((prevContact: Contact) => {
                    if (prevContact.id === Number(contactId)) {
                        return newContact;
                    }
                    return prevContact;
                }));
                break;
            case "delete":
                setContacts((prevContacts: Contact[]) => prevContacts.filter((prevContact: Contact) => prevContact.id !== Number(contactId)));
                break;
            default:
                console.log("Oups, problème");
        }
        navigate("/contacts");
    }

    return (
        <>
            {errorMessage && <h1>{errorMessage}</h1>}
            {!errorMessage &&
                <form onSubmit={submitHandler}>
                    <label htmlFor="firstname" className="form-label">Prénom : </label>
                    <input type="text" className="form-control" placeholder="Prénom" id="firstname" disabled={mode === "delete"} ref={firstnameRef} />
                    <label htmlFor="lastname" className="form-label mt-2">Nom : </label>
                    <input type="text" className="form-control" placeholder="Nom" id="lastname" disabled={mode === "delete"} ref={lastnameRef} />
                    <label htmlFor="mail" className="form-label mt-2">Adresse e-mail : </label>
                    <input type="email" className="form-control" placeholder="Adresse e-mail" id="mail" disabled={mode === "delete"} ref={emailRef} />
                    <label htmlFor="phone" className="form-label mt-2">Numéro de téléphone : </label>
                    <input type="phone" className="form-control" placeholder="Numéro de téléphone" id="phone" disabled={mode === "delete"} ref={phoneRef} />
                    <hr />
                    {<button type="submit" className={`btn btn-${mode === "edit" ? "warning" : "danger"} d-block ms-auto`}>{
                        mode === "create" ? "Ajouter" :
                            mode === "edit" ? "Modifier" :
                                "Supprimer"
                    }</button>}
                </form>
            }
        </>
    );
}

export default CreateEditOrDeleteContactComponent;
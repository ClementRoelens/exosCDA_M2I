import { useNavigate, useParams, useSearchParams } from "react-router-dom";
import { Contact } from "../models/contact";
import { useContext, useEffect, useState } from "react";
import { ContactContext } from "../context/ContactContext";
import "bootstrap-icons/font/bootstrap-icons.min.css";

function CreateEditOrDeleteContactComponent() {
    const [searchParams] = useSearchParams();
    const mode = searchParams.get("mode");
    const params = useParams();
    const { contactId } = params;
    const [contact, setContact] = useState<Contact | null>(null);
    const { contacts } = useContext(ContactContext);
    // const navigate = useNavigate();

    const [errorMessage, setErrorMessage] = useState<string>("");

    useEffect(() => {
        // Create, edit or delete

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
            }
        }
    }, []);


    return (
        <>
            {errorMessage && <h1>{errorMessage}</h1>}
            {!errorMessage &&
                <form>
                    <label htmlFor="firstname" className="form-label">Prénom : </label>
                    <input type="text" className="form-control" placeholder="Prénom" id="firstname" disabled={mode !== "delete"}/>
                    <label htmlFor="lastname" className="form-label">Nom : </label>
                    <input type="text" className="form-control" placeholder="Nom" id="lastname" disabled={mode !== "delete"}/>
                    <label htmlFor="mail" className="form-label">Adresse e-mail : </label>
                    <input type="email" className="form-control" placeholder="Adresse e-mail" id="mail" disabled={mode !== "delete"}/>
                    <label htmlFor="phone" className="form-label">Numéro de téléphone : </label>
                    <input type="phone" className="form-control" placeholder="Numéro de téléphone" id="phone" disabled={mode !== "delete"}/>
                    <hr />
                    <button type="submit" className="btn btn-success d-block ms-auto">{
                        mode === "create" ? "Ajouter" : 
                            mode === "edit" ? "Modifier" :
                                "Supprimer"
                    }</button>
                </form>
            }
        </>
    );
}

export default CreateEditOrDeleteContactComponent;
import { Person } from "../Person.interface";

const TableComponent = (prop: TableauProp) => {
    return (
        <>
            {prop.personnesArray.length > 0 ?
                <>
                    <table className="table table-striped border rounded">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nom</th>
                                <th>Prénom</th>
                            </tr>
                        </thead>
                        <tbody>
                            {prop.personnesArray.map((personne: Person) =>
                                <tr key={personne.id}>
                                    <td>{personne.id}</td>
                                    <td>{personne.firstname}</td>
                                    <td>{personne.lastname}</td>
                                </tr>)}
                        </tbody>
                    </table>
                </>
                :
                <>
                    <p className="border rounded text-center">Aucune personne n'a été envoyée</p>
                </>}
        </>
    );
}

interface TableauProp {
    personnesArray: Person[];
}

export default TableComponent;
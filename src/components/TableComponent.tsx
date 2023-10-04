import { Person } from "./FormComponent";
import TableStyles from "./TableComponent.module.css";

function TableComponent(props:TableComponentProps){
    if (props.transmittedPersonArray.length > 0) {
        return(
            <table className={`${TableStyles.table} ${TableStyles.content}`}>
                <thead>
                    <tr>
                        <th className={TableStyles.cell}>Nom</th>
                        <th className={TableStyles.cell}>Prénom</th>
                        <th className={TableStyles.cell}>Âge</th>
                    </tr>
                </thead>
                <tbody>
                    {props.transmittedPersonArray.map((person:Person,index:number) => 
                    <tr key={index}>
                        <td className={TableStyles.cell}>{person.firstname}</td>
                        <td className={TableStyles.cell}>{person.lastname}</td>
                        <td className={TableStyles.cell}>{person.age}</td>
                    </tr>
                    )}
                </tbody>
            </table>
        );
    }
    return (
        <p className={TableStyles.content}>Le tableau est vide</p>
    );
}

interface TableComponentProps{
    transmittedPersonArray:Person[];
}

export default TableComponent;
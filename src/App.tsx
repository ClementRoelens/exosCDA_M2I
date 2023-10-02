import './App.css';
import { Person } from './Person.interface';
import TableComponent from './components/TableComponent';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  const personnes:Person[] = [
    {
      firstname:"Clément",
      lastname:"Roelens",
      id:0
    },
    {
      firstname:"Nassim",
      lastname:"Sakhri",
      id:1
    },
    {
      firstname:"Olivia",
      lastname:"Pigani",
      id:2
    },
    {
      firstname:"Clémence",
      lastname:"Petit",
      id:3
    }
  ];

  const scientists:Person[] = [
    {
      firstname:"Albert",
      lastname:"Einstein",
      id:0
    },
    {
      firstname:"Robert",
      lastname:"Oppenheimer",
      id:1
    },
    {
      firstname:"Robert",
      lastname:"Rosenthal",
      id:2
    },
    {
      firstname:"António",
      lastname:"Damásio",
      id:3
    },
    {
      firstname:"Leonard",
      lastname:"Hayflick",
      id:4
    }
  ];
  return (
    <main className='container'>
      <TableComponent personnesArray={personnes}/>
      <TableComponent personnesArray={scientists} />
      <TableComponent personnesArray={[]}/>
    </main>
  );
}

export default App;

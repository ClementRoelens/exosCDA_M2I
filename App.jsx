import { View, Text } from 'react-native'
import React, { useEffect, useState } from 'react'
import Geolocation from '@react-native-community/geolocation'

export default function App() {

  const [town, setTown] = useState("");


  useEffect(() => {
    Geolocation.requestAuthorization();    
    Geolocation.getCurrentPosition(position => {
      // console.log("Coordonnées obtennues : ", position.coords.latitude);
      // setCoords({latitude : position.coords.latitude, longitude : position.coords.longitude});
      const BASE_URL = "http://dataservice.accuweather.com/locations/v1/cities/geoposition/search?apikey=L4jtHG0rBF9fQk8mwVOFVbUqfsNDhjGg&q=" + position.coords.latitude + "%2C" + position.coords.longitude + "&language=fr-fr&details=true&toplevel=false";

      fetch(BASE_URL).then(res => {
        console.log(res);
        console.log("Données reçues");
        res.json().then(data => {
          console.log("Données converties");
          console.log(data);
          setTown(data.LocalizedName);
        })
          .catch(error => console.error("Erreur lors de la conversion des données en JSON", error));
      })
        .catch(error => console.error("Erreur lors de la récupération des données", error));
    }, error => console.error("Erreur lors de la récupération des coordonnées", error),
      { enableHighAccuracy: true, timeout: 30000, maximumAge: 1000 });
  }, []);

  return (
    <View>
      <Text>Vous êtes à {town}</Text>
    </View>
  )
}
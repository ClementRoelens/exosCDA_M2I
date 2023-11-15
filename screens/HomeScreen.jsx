import { Button, StyleSheet, TextInput, View, Text } from 'react-native'
import React, { useState } from 'react'
import { useSelector, useDispatch } from 'react-redux';
import { getAllPokemons } from '../components/pokemonSlice';

const HomeScreen = ({ navigation }) => {
    const pokemons = useSelector(state => state.pokemon.pokemons);
    const favedPokemons = useSelector(state => state.pokemon.favedPokemons);
    const loading = useSelector(state => state.pokemon.loading);
    const loaded = useSelector(state => state.pokemon.loaded);

    const dispatch = useDispatch();
    const [pokemonNumber, setPokemonNumber] = useState(0);

    return (
        <View style={styles.container}>
            {(!loading && !loaded) && <>
                <Text>Entrez combien de Pokémons vous voulez chargez</Text>
                <TextInput style={styles.input} inputMode="numeric" onChangeText={e => setPokemonNumber(e)} />
                <Button title="Charger" onPress={() => dispatch(getAllPokemons(pokemonNumber))} />
            </>
            }
            {loading &&
                <Text style={styles.text}>Chargment en cours...</Text>
            }
            {loaded &&
                <>
                    <Button title="Liste de tous les Pokémons" onPress={() => navigation.navigate("List", { mode: "All" })} />
                    <Button title="Favoris" onPress={() => navigation.navigate("Faved", { mode: "Faved" })} />
                </>}
        </View>
    );
}

export default HomeScreen

const styles = StyleSheet.create({
    container: {
        marginHorizontal: 30,
        marginTop: 10,
        height: 100,
        gap: 10
    },
    input: {
        borderWidth: 1,
        borderColor: "gray",
        borderRadius: 15,
        paddingStart: 10,
        width: "20%",
        alignSelf: "center",
        textAlign: "center"
    },
    text: {
        textAlign: "center"
    }
})
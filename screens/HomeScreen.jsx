import { Button, StyleSheet, View } from 'react-native'
import React, { useEffect } from 'react'
import { useSelector, useDispatch } from 'react-redux';
import { getAllPokemons } from '../components/pokemonSlice';

const HomeScreen = ({ navigation }) => {
    const pokemons = useSelector(state => state.pokemon.pokemons);
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(getAllPokemons());
    }, []);

    return (
        <View style={styles.container}> 
            <Button title="Liste de tous les Pokémons" onPress={() => navigation.navigate("List", { pokemons: pokemons })} navigation={navigation} />
            <Button title="Favoris" onPress={() => navigation.navigate("Faved")} />
        </View>
    );
}

export default HomeScreen

const styles = StyleSheet.create({
    container: {
        marginHorizontal: 30,
        marginTop:10,
        justifyContent:"space-around",
        height:100
    }
})
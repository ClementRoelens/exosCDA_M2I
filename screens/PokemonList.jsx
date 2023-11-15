import { FlatList, StyleSheet, View } from 'react-native'
import React, { useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { resetFamily } from '../components/pokemonSlice';
import PokemonThumb from '../components/PokemonThumb';
import { useFocusEffect } from '@react-navigation/native';

const PokemonList = ({ route, navigation }) => {
    const mode = route.params.mode;
    const dispatch = useDispatch();
    const pokemons = useSelector(state => state.pokemon.pokemons);
    const favedPokemons = useSelector(state => state.pokemon.favedPokemons);
    const [displayedPokemons, setDisplayedPokemons] = useState([]);

    useFocusEffect(
        React.useCallback(() => {
            dispatch(resetFamily());
            if (mode === "Faved") {
                setDisplayedPokemons(favedPokemons);
            } else {
                setDisplayedPokemons(pokemons);
            }
        }, [favedPokemons, mode, pokemons])
    );

    return (
        <View>
            <FlatList data={displayedPokemons} numColumns={2} contentContainerStyle={styles.list} keyExtractor={(item, index) => index} renderItem={({ item }) =>
                <PokemonThumb navigation={navigation} style={styles.pokemon} pokemon={item} />} />
        </View>
    )
}

export default PokemonList

const styles = StyleSheet.create({
    list: {
        alignItems: "center",
    }
})
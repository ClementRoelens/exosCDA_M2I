import { FlatList, StyleSheet, View } from 'react-native'
import React, { useEffect } from 'react'
import { useDispatch } from 'react-redux';
import { resetFamily } from '../components/pokemonSlice';
import PokemonThumb from '../components/PokemonThumb';

const PokemonList = ({route, navigation}) => {
    const pokemons = route.params.pokemons;
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(resetFamily());
    }, []);

    return (
        <View>
            <FlatList data={pokemons} numColumns={2} contentContainerStyle={styles.list} keyExtractor={(item, index) => index} renderItem={({ item }) =>
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
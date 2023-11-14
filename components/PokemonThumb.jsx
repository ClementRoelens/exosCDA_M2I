import { StyleSheet, Text, View, Pressable, Image, FlatList } from 'react-native'
import React, { useEffect, useState } from 'react';

const PokemonThumb = ({ pokemon, navigation }) => {

    function getTextColor() {
        switch (pokemon.color) {
            case "white":
            case "yellow":
            case "pink" :
                return "black";
            default:
                return "white";
        }
    }

    return (
        <Pressable onPress={() => navigation.navigate("PokemonDetail", {pokemon : pokemon, textColor : getTextColor() })}>
            {pokemon &&
                <View style={[styles.container, { backgroundColor: pokemon.color }]}>
                    <View>
                        <Text style={[styles.text, styles.name, {color : getTextColor()}]}>{pokemon.name}</Text>
                        <FlatList data={pokemon.types} keyExtractor={(item, index) => index} renderItem={({ item }) => {
                            return (
                                <View style={styles.typeContainer}>
                                    <Text style={[styles.text, styles.typeText, {color : getTextColor()}]}>{item}</Text>
                                </View>
                            )
                        }} />
                    </View>
                    <Image style={styles.image} source={{ uri: pokemon.sprite }} />
                </View>}
        </Pressable>
    )
}

export default PokemonThumb

const styles = StyleSheet.create({
    container: {
        borderRadius: 25,
        paddingHorizontal: 20,
        paddingVertical: 10,
        marginVertical: 7,
        marginHorizontal: 7,
        flexDirection: "row",
        width: 185,
        height:120
    },
    image: {
        width: 85,
        height: 85,
        alignSelf:"center"
    },
    name: {
        fontWeight: "bold",
        fontSize: 15,
        marginBottom: 4
    },
    typeContainer: {
        borderRadius: 15,
        backgroundColor: "rgba(255,255,255,0.2)",
        padding: 7,
        marginVertical: 3
    },
    text: {
        textTransform: "capitalize"
    },
    typeText: {
        opacity: 1,
        textAlign: "center",
        fontSize:12
    }
});
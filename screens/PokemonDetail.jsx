import { StyleSheet, Text, View, Pressable, FlatList, Image } from 'react-native'
import React, { useEffect } from 'react'
import { Shadow } from 'react-native-shadow-2';
import { useDispatch, useSelector } from 'react-redux';
import { addToFavs, getFamily, isFaved, removeFromFavs } from '../components/pokemonSlice';
import EvolutionCard from '../components/EvolutionCard';

const PokemonDetail = ({ route }) => {
    const pokemon = route.params.pokemon;
    const textColor = route.params.textColor;
    const family = useSelector(state => state.pokemon.selectedFamily);
    const stateIsFaved = useSelector(state => state.pokemon.isFaved);
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(getFamily(pokemon.evolutionChainUrl));
        dispatch(isFaved(pokemon.id));
    }, []);

    async function favClick() {
        if (stateIsFaved) {
            await dispatch(removeFromFavs(pokemon));
        } else {
            await dispatch(addToFavs(pokemon));
        }
        dispatch(isFaved(pokemon.id));
    }

    return (
        <FlatList ListHeaderComponent={
            <>
                <View style={[styles.overview, { backgroundColor: pokemon.color }]}>
                    <Text style={[styles.text, { color: textColor }, styles.title]}>{pokemon.name}</Text>
                    <FlatList data={pokemon.types} keyExtractor={(item, index) => index} renderItem={({ item }) => {
                        return (
                            <View style={styles.typeContainer}>
                                <Text style={[styles.text, styles.typeText, { color: textColor }]}>{item}</Text>
                            </View>
                        )
                    }} />
                    <Image style={styles.imageOverview} source={{ uri: pokemon.sprite }} />
                </View>
                <View style={styles.descriptionContainer}>
                    <Text style={[styles.title, styles.blackText]}>Détails</Text>
                    <Text>{pokemon.description}</Text>
                </View>
                <View style={styles.shadowContainer}>
                    <Shadow
                        distance={8}
                        startColor="rgba(40,40,40,0.15)"
                        endColor="rgba(40,40,40,0)"
                        radius={10}
                        stretch={true}
                    >
                        <View style={styles.sizeContainer}>
                            <View>
                                <Text style={styles.sizeTitle}>Height</Text>
                                <Text>{pokemon.height / 10} m</Text>
                            </View>
                            <View>
                                <Text style={styles.sizeTitle}>Weight</Text>
                                <Text>{pokemon.weight / 10} kg</Text>
                            </View>
                        </View>
                    </Shadow>
                </View>
                <View style={styles.buttonContainer}>
                    <Shadow
                        distance={4}
                        startColor="rgba(20,20,20,0.2)"
                        endColor="rgba(20,20,20,0)"
                        radius={10}
                    >
                        <Pressable style={[styles.button, { backgroundColor: pokemon.color }]} onPress={favClick}>
                            {!stateIsFaved ?
                                <Text style={[styles.buttonText, { color: textColor }]}>Ajouter aux favoris</Text>
                                :
                                <Text style={[styles.buttonText, { color: textColor }]}>Retirer des favoris</Text>
                            }
                        </Pressable>
                    </Shadow>
                </View>
                <View style={styles.familyContainer}>
                    <Text style={[styles.title, styles.blackText]}>Chaîne d'évolutions</Text>
                </View>
            </>}
            data={family} contentContainerStyle={styles.evolutions} keyExtractor={(item, index) => index} renderItem={({ item }) => {
                return (
                    <View>
                        <EvolutionCard pokemon={item} />
                    </View>
                )
            }} />

    )
}

export default PokemonDetail

const styles = StyleSheet.create({
    overview: {
        paddingTop: 15,
        paddingStart: 15,
        height: 230
    },
    typeContainer: {
        borderRadius: 15,
        backgroundColor: "rgba(255,255,255,0.2)",
        padding: 4,
        marginVertical: 3,
        maxWidth: 100
    },
    text: {
        textTransform: "capitalize"
    },
    typeText: {
        opacity: 1,
        textAlign: "center",
        fontSize: 12
    },
    imageOverview: {
        width: 200,
        height: 200,
        alignSelf: "center",
        position: "absolute",
        top: 30
    },
    descriptionContainer: {
        padding: 15
    },
    title: {
        fontSize: 25,
        fontWeight: "bold",
        marginBottom: 5
    },
    blackText: {
        color: "black"
    },
    shadowContainer: {
        paddingHorizontal: 35,
        marginTop: 10,
        marginBottom: 20
    },
    sizeContainer: {
        flexDirection: "row",
        justifyContent: "space-around",
        alignContent: "center",
        paddingVertical: 6,
    },
    sizeTitle: {
        marginBottom: 7,
        color: "gray"
    },
    buttonContainer: {
        alignSelf: "center",
        marginTop: 5,
        marginBottom: 15
    },
    button: {
        borderRadius: 100,
        width: 120,
        height: 70,
        // borderWidth:1,
        // borderColor:"black",
        alignSelf: "center",
        justifyContent: "center"
    },
    buttonText: {
        alignSelf: "center",
        textAlign: "center",
        fontSize: 15
    },
    familyContainer: {
        marginHorizontal: 15
    },
    evolutions: {
        alignItems: "center",
        paddingBottom: 20
    }
})
import { StyleSheet, Text, View, Image } from 'react-native'
import React from 'react'

const EvolutionCard = ({pokemon}) => {
  return (
    <View style={styles.container}>
        <Image style={styles.image} source={{uri:pokemon.sprite}}/>
      <Text style={styles.text}>{pokemon.name}</Text>
    </View>
  )
}

export default EvolutionCard

const styles = StyleSheet.create({
    image : {
        width:100,
        height:100
    },
    container : {
        alignContent:"center",
        marginVertical:5
    },
    text : {
        textTransform:"capitalize",
        textAlign:"center"
    }
})
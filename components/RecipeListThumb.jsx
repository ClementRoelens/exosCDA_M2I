import { StyleSheet , Pressable, Text } from 'react-native'
import React from 'react'

const RecipeListThumb = ({ navigation, color, id, children }) => {
    return (
        <Pressable onPress={() => navigation.navigate("MealsOverview", {id : id} )} style={[styles.container, { backgroundColor: color }]}>
            <Text style={styles.text}>{children}</Text>
        </Pressable>
    )
}

export default RecipeListThumb

const styles = StyleSheet.create({
    container: {
        borderRadius: 10,
        alignItems:"center",
        justifyContent:"center",
        width:170,
        height:170,
        margin:15
    }, 
    text : {
        textAlign:"center",
        fontSize:20,
        fontWeight:"bold"
    }
})
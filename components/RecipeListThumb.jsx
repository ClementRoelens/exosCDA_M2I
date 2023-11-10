import { StyleSheet , Pressable, Text } from 'react-native'
import React from 'react'

const RecipeListThumb = ({ navigation, color, children }) => {
    return (
        <Pressable onPress={() => navigation.navigate("RecipeDetail", recipe)} style={[styles.container, { backgroundColor: color }]}>
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
        width:150,
        height:150
    }, 
    text : {
        textAlign:"center",
        fontSize:20,
        fontWeight:"bold"
    }
})
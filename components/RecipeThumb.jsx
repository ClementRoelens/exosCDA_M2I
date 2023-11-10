import { StyleSheet, Text, View, Pressable, Image } from 'react-native'
import React from 'react'

const RecipeThumb = ({ recipe }) => {
  return (
    <Pressable style={styles.recipe}>
      <Image  width="100%" height={200} source={{ uri: recipe.imageUrl }} />
      <Text style={styles.title}>{recipe.title}</Text>
      <View style={styles.footer}>
        <Text style={styles.footerText}>{recipe.duration}m</Text>
        <Text style={styles.footerText}>{recipe.complexity.toUpperCase()}</Text>
        <Text style={styles.footerText}>{recipe.affordability.toUpperCase()}</Text>
      </View>
    </Pressable>
  )
}

export default RecipeThumb;

const styles = StyleSheet.create({
  recipe: {
    alignItems: "center",
    paddingBottom: 10,
    borderRadius: 10,
    overflow: "hidden",
    backgroundColor: "white"
  },
  title: {
    fontSize: 25,
    textAlign: "center",
    fontWeight: "500",
    marginVertical: 10
  },
  footer: {
    fontSize: 25,
    flexDirection: "row",
    justifyContent: "space-around",
    width: "50%"
  },
  footerText: {
    color:"black"
  }
});


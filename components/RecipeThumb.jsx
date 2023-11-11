import { StyleSheet, Text, View, Pressable, Image } from 'react-native'
import React from 'react'
import { Shadow } from 'react-native-shadow-2';

const RecipeThumb = ({ recipe, navigation }) => {
  return (
    <Shadow 
          distance={2} 
          startColor="rgba(60,60,60,0.1)"
          endColor="rgba(60,60,60,0)"
          radius={10}
          stretch={true}
          containerStyle={styles.container}
          >
      <Pressable style={styles.recipe} onPress={() => navigation.navigate("About the Meal", {id:recipe.id})}>
        <Image width={"100%"} height={200} source={{ uri: recipe.imageUrl }} />
        <Text style={styles.title}>{recipe.title}</Text>
        <View style={styles.footer}>
          <Text style={styles.footerText}>{recipe.duration}m</Text>
          <Text style={styles.footerText}>{recipe.complexity.toUpperCase()}</Text>
          <Text style={styles.footerText}>{recipe.affordability.toUpperCase()}</Text>
        </View>
      </Pressable>
    </Shadow>
  )
}

export default RecipeThumb;

const styles = StyleSheet.create({
  recipe: {
    alignItems: "center",
    paddingBottom: 10,
    borderRadius: 10,
    overflow: "hidden",
    backgroundColor: "white",
    shadowColor: "black",
    elevation: 4,
  },
  container : {
    margin:3
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
    color: "black"
  }
});


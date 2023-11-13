import { StyleSheet, Text, View, Image, FlatList, Pressable } from 'react-native'
import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { favAction, selectOneRecipe } from '../components/recipeSlice';

const RecipeDetail = ({ route }) => {
  const id = route.params.id;
  const recipe = useSelector(state => state.recipe.selectedRecipe);
  const favedRecipes = useSelector(state => state.recipe.favedRecipes);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(selectOneRecipe(id));
  }, [id]);

  return (<>
    {recipe &&
      <View style={styles.global}>
        <FlatList
          ListHeaderComponent={
            <>
              <Image style={styles.image} source={{ uri: recipe.imageUrl }} />
              <Text style={styles.title}>{recipe.title}</Text>
              <View style={styles.annotations}>
                <Text style={styles.annotationsText}>{recipe.duration}m</Text>
                <Text style={styles.annotationsText}>{recipe.complexity.toUpperCase()}</Text>
                <Text style={styles.annotationsText}>{recipe.affordability.toUpperCase()}</Text>
              </View>
              <Pressable style={styles.faved} onPress={() => dispatch(favAction(recipe))}>
                <Text style={styles.annotationsText}>{favedRecipes.find(favedRecipe => favedRecipe.id === recipe.id) ? "Retirer des " : "Ajouter aux "}favoris</Text>
                <Image style={[styles.notFavedIcon, styles.favedIcon]}
                  source={favedRecipes.find(favedRecipe => favedRecipe.id === recipe.id) ? require("../assets/heart_filled.png") : require("../assets/heart.png")} />
              </Pressable>
              <Text style={styles.listTitle}>Ingredients</Text>
              <FlatList data={recipe.ingredients} renderItem={itemData => {
                return (
                  <View style={styles.listItem}>
                    <Text style={styles.listText}>{itemData.item}</Text>
                  </View>
                )
              }}
                keyExtractor={(item, index) => index} />
              <Text style={styles.listTitle}>Steps</Text>
            </>
          }
          data={recipe.steps} renderItem={itemData => {
            return (
              <View style={styles.listItem}>
                <Text style={styles.listText}>{itemData.item}</Text>
              </View>
            )
          }}
          keyExtractor={(item, index) => index}
          ListFooterComponent={<></>} />
      </View>
    }
  </>
  )
}

export default RecipeDetail

const styles = StyleSheet.create({
  global: {
    marginBottom: 20
  },
  annotations: {
    fontSize: 25,
    flexDirection: "row",
    justifyContent: "space-around",
    width: "47%",
    alignSelf: "center",
    marginTop: 7
  },
  annotationsText: {
    color: "black"
  },
  title: {
    fontSize: 27,
    textAlign: "center",
    fontWeight: "600",
    marginVertical: 10,
    color: "black"
  },
  image: {
    width: "100%",
    height: 300
  },
  faved: {
    width: "45%",
    flexDirection: "row",
    justifyContent: "space-around",
    alignSelf: "center",
    alignItems: "center",
    marginTop: 20
  },
  notFavedIcon: {
    width: 35,
    height: 30,
  },
  listTitle: {
    fontSize: 25,
    fontWeight: "600",
    color: "#e1b497",
    textAlign: "center",
    marginTop: 20,
    borderBottomColor: "#e1b497",
    borderBottomWidth: 2,
    paddingBottom: 5,
    marginBottom: 5,
    width: "75%",
    alignSelf: "center"
  },
  listItem: {
    width: "75%",
    alignSelf: "center",
    borderRadius: 7,
    backgroundColor: "#e1b497",
    marginVertical: 4,
    padding: 8,
    alignItems: "center"
  },
  listText: {
    color: "black",
    fontSize: 15,
    textAlign: "center"
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
})
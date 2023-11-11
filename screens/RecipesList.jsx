import { StyleSheet, FlatList, Text, View, Image, Pressable } from 'react-native'
import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { selectRecipesFromOneCategory } from '../components/recipeSlice';
import RecipeThumb from '../components/RecipeThumb';

const RecipesList = ({ route, navigation }) => {
  const categoryId = route.params.id;
  const recipes = useSelector(state => state.recipe.recipes);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(selectRecipesFromOneCategory(categoryId));
  }, []);

  return (
    <FlatList data={recipes} contentContainerStyle={styles.listContent} renderItem={itemData => {
      return <RecipeThumb recipe={itemData.item} navigation={navigation}/>
    }} />
  )
}

export default RecipesList

const styles = StyleSheet.create({
  listContent: {
    alignSelf: "center",
    width: "85%",
    gap:30,
    paddingTop:15
  }
});
import { StyleSheet, FlatList, Text, View,Image } from 'react-native'
import React, {useEffect} from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { selectRecipesFromOneCategory } from '../components/recipeSlice';

const RecipesList = (route) => {
  const categoryId = route.params.id;
  const recipes = useSelector(state => state.recipe.recipes);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(selectRecipesFromOneCategory(categoryId));
  },[]);

  return (
   <FlatList data={recipes} renderItem={itemData => {
    return (
    <View>
      <Image />
    </View>
    )}}/>
  )
}

export default RecipesList

const styles = StyleSheet.create({})
import { StyleSheet, ScrollView, FlatList } from 'react-native'
import React, { useEffect } from 'react'
import RecipeListThumb from '../components/RecipeListThumb'
import { useDispatch, useSelector } from 'react-redux'
import { getCategories } from '../components/categorySlice'

const Home = () => {
  // const recipes = useSelector(state => state.recipe.recipes);
  const categories = useSelector(state => state.category.categories);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getCategories());
  }, []);

  return (
    <FlatList style={styles.global} 
              contentContainerStyle={styles.listContent}
              data={categories} 
              numColumns={2} 
              renderItem={itemData => {
      return <RecipeListThumb color={itemData.item.color} style={styles.thumb}>{itemData.item.title}</RecipeListThumb>
    }}/>
  )
}

export default Home;

const styles = StyleSheet.create({
  global: {
    flex:1,
    marginTop:10
  },
  listContent : {
    alignItems:"center",
    gap : 15
  }
})
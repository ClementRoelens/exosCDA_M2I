import { StyleSheet, FlatList } from 'react-native'
import React, { useEffect } from 'react'
import RecipeListThumb from '../components/RecipeListThumb'
import { useDispatch, useSelector } from 'react-redux'
import { getCategories } from '../components/categorySlice'

const Home = ({ navigation }) => {
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
        return (
          <RecipeListThumb
              navigation={navigation}
              color={itemData.item.color}
              id={itemData.item.id}
              style={styles.thumb}>
              {itemData.item.title}
          </RecipeListThumb>)
      }}
      keyExtractor={item => item.id} />
  )
}

export default Home;

const styles = StyleSheet.create({
  global: {
    marginTop: 10
  },
  listContent: {
    alignItems: "center"
  },
  thumb: {
  }
})
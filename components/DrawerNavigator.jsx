import { StyleSheet, Text, View } from 'react-native';
import React from 'react';
import { createDrawerNavigator } from '@react-navigation/drawer';
import StackNavigator from './StackNavigator';
import RecipeDetail from '../screens/RecipeDetail';
import { NavigationContainer } from '@react-navigation/native'
import { useSelector } from 'react-redux';

const DrawerNavigator = () => {
    const Drawer = createDrawerNavigator();
  const favedRecipes = useSelector(state => state.recipe.favedRecipes);
    return (
        <NavigationContainer>
            <Drawer.Navigator initialRouteName='Drawer'>
                <Drawer.Screen name="Home" component={StackNavigator} options={{ headerShown: false }} />
                {favedRecipes.map(recipe =>
                    <Drawer.Screen name={recipe.title} component={RecipeDetail} key={recipe.id} initialParams={{ id: recipe.id }} />)}
            </Drawer.Navigator>
        </NavigationContainer>
    )
}

export default DrawerNavigator

const styles = StyleSheet.create({})
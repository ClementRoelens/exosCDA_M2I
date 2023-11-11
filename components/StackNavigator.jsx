import { StyleSheet } from 'react-native'
import React from 'react'
import { createNativeStackNavigator } from '@react-navigation/native-stack'
import Home from '../screens/Home';
import RecipesList from '../screens/RecipesList';
import RecipeDetail from '../screens/RecipeDetail';


const StackNavigator = () => {
    const Stack = createNativeStackNavigator();

    return (
        <Stack.Navigator initialRouteName='All Categories'>
            <Stack.Screen name="All Categories" component={Home} />
            <Stack.Screen name="MealsOverview" component={RecipesList}/>
            <Stack.Screen name="About the Meal" component={RecipeDetail}/>
        </Stack.Navigator>
    )
}

export default StackNavigator

const styles = StyleSheet.create({})
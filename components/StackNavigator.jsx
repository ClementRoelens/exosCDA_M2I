import { StyleSheet } from 'react-native'
import React from 'react'
import { createNativeStackNavigator } from '@react-navigation/native-stack'
import Home from '../screens/Home';
import RecipesList from '../screens/RecipesList';


const StackNavigator = () => {
    const Stack = createNativeStackNavigator();

    return (
        <Stack.Navigator initialRouteName='All Categories'>
            <Stack.Screen name="All Categories" component={Home} />
            <Stack.Screen name="Italian" component={RecipesList} options={{title:"MealsOverview"}}/>
            <Stack.Screen name="Quick & Easy" component={RecipesList} options={{title:"MealsOverview"}}/>
            <Stack.Screen name="Hamburgers" component={RecipesList} options={{title:"MealsOverview"}}/>
            <Stack.Screen name="German" component={RecipesList} options={{title:"MealsOverview"}}/>
            <Stack.Screen name="Light & Lovely" component={RecipesList} options={{title:"MealsOverview"}}/>
            <Stack.Screen name="Exotic" component={RecipesList} options={{title:"MealsOverview"}}/>
            <Stack.Screen name="Breakfast" component={RecipesList} options={{title:"MealsOverview"}}/>
            <Stack.Screen name="Asian" component={RecipesList} options={{title:"MealsOverview"}}/>
            <Stack.Screen name="French" component={RecipesList} options={{title:"MealsOverview"}}/>
            <Stack.Screen name="Summer" component={RecipesList} options={{title:"MealsOverview"}}/>
        </Stack.Navigator>
    )
}

export default StackNavigator

const styles = StyleSheet.create({})
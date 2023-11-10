import { StyleSheet, FlatList } from 'react-native'
import React from 'react'
import { createNativeStackNavigator } from '@react-navigation/native-stack'
import Home from '../screens/Home';
import { useSelector } from 'react-redux';
import RecipesList from '../screens/RecipesList';


const StackNavigator = () => {
    const categories = useSelector(state => state.category.categories);
    const Stack = createNativeStackNavigator();

    return (
        <Stack.Navigator initialRouteName='Home'>
            <Stack.Screen name="All Categories" component={Home} />
            <Stack.Screen name="Italian" component={RecipesList}/>
            <Stack.Screen name="Quick & Easy" component={RecipesList}/>
            <Stack.Screen name="Hamburgers" component={RecipesList}/>
            <Stack.Screen name="German" component={RecipesList}/>
            <Stack.Screen name="Light & Lovely" component={RecipesList}/>
            <Stack.Screen name="Exotic" component={RecipesList}/>
            <Stack.Screen name="Breakfast" component={RecipesList}/>
            <Stack.Screen name="Asian" component={RecipesList}/>
            <Stack.Screen name="French" component={RecipesList}/>
            <Stack.Screen name="Summer" component={RecipesList}/>
        </Stack.Navigator>
    )
}

export default StackNavigator

const styles = StyleSheet.create({})
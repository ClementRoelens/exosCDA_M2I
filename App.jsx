import { StyleSheet } from 'react-native'
import React from 'react'
import { store } from './config/store';
import { Provider } from 'react-redux';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import HomeScreen from './screens/HomeScreen';
import PokemonDetail from './screens/PokemonDetail';
import PokemonList from './screens/PokemonList';


const Stack = createNativeStackNavigator();

const App = () => {
  return (
    <Provider store={store}>
      <NavigationContainer>
        <Stack.Navigator initialRouteName="Home">
          <Stack.Screen name="Home" component={HomeScreen}/>
          <Stack.Screen name="List" component={PokemonList}/>
          <Stack.Screen name="Faved" component={PokemonList}/>
          <Stack.Screen name="PokemonDetail" component={PokemonDetail}/>
        </Stack.Navigator>
      </NavigationContainer>
    </Provider>
  )
}

export default App

const styles = StyleSheet.create({})
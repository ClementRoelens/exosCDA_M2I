import { StyleSheet } from 'react-native'
import React from 'react'
import { NavigationContainer } from '@react-navigation/native'
import { createDrawerNavigator } from '@react-navigation/drawer';
import { Provider } from 'react-redux';
import { store } from './redux-config/store';
import StackNavigator from './components/StackNavigator';

export default function App() {

  const Drawer = createDrawerNavigator();

  return (
    <Provider store={store}>
      <NavigationContainer>
        <Drawer.Navigator initialRouteName='Drawer'>
          <Drawer.Screen name="Drawer" component={StackNavigator} options={{headerShown:false}}/>
        </Drawer.Navigator>
      </NavigationContainer>
    </Provider>
  )
}

const styles = StyleSheet.create({})
import { StyleSheet } from 'react-native'
import React from 'react'


import { Provider } from 'react-redux';
import { store } from './redux-config/store';
import DrawerNavigator from './components/DrawerNavigator';


export default function App() {

  

  return (
    <Provider store={store}>
      <DrawerNavigator/>
    </Provider>
  )
}

const styles = StyleSheet.create({})
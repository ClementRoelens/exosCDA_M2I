import { Button, StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { HomeNavigationProps } from '../config-interfaces'

interface HomeProps {
    navigation: HomeNavigationProps;
}

const HomeScreen: React.FC<HomeProps> = ({ navigation }) => {
    return (
        <View>
            <Button title="Clément" onPress={() => navigation.navigate("DetailScreen", { contact: { firstname: "Clément", lastname: "Roelens", phonenumber: "0615616564" } })} />
            <Button title="Nassim" onPress={() => navigation.navigate("DetailScreen", { contact: { firstname: "Nassim", lastname: "Sahkri", phonenumber: "0765415343545" } })}/>
        </View>
    )
}

export default HomeScreen

const styles = StyleSheet.create({})
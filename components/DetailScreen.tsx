import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { DetailRouteProps } from '../config-interfaces'

interface DetailProps {
    route: DetailRouteProps;
}

const DetailScreen: React.FC<DetailProps> = ({ route }) => {
    const contact = route.params.contact;

    return (
        <View>
            <Text>Nom : {contact.lastname}</Text>
            <Text>Prénom : {contact.firstname}</Text>
            <Text>Numéro de téléphone : {contact.phonenumber}</Text>
        </View>
    )
}

export default DetailScreen

const styles = StyleSheet.create({})
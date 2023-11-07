import { Button, Modal, Pressable, StyleSheet, Text, TextInput, View } from 'react-native'
import React, { useState } from 'react'
import { Article } from '../models/Article';

interface ArticleDetailInterface {
    article: Article;
    visible: boolean;
    deleteHandler: () => void;
    close: () => void;
}


const ArticleDetail = (props: ArticleDetailInterface) => {

    return (
        <Modal visible={props.visible}>
            <View style={styles.global}>
                <Text style={styles.name}>{props.article.name}</Text>
                <Text>{props.article.price}€</Text>
                <Text style={styles.priceArea}>Quantité : {props.article.quantity}  |  Prix total : {props.article.price*props.article.quantity}€</Text>
                {/* <TextInput inputMode='numeric' onChangeText={e => setQuantity(+e)} defaultValue={props.article.quantity.toString()} /> */}
                <Button title='Supprimer' color="red" onPress={props.deleteHandler} />
                <Pressable style={styles.cancel} onPress={props.close}>
                    <Text style={styles.cancelText}>RETOUR</Text>
                    </Pressable>
            </View>
        </Modal>
    )
}

export default ArticleDetail

const styles = StyleSheet.create({
    quantity: {
        flexDirection: "row"
    },
    global: {
        flex: 1,
        justifyContent: "center",
        alignItems:"center",
        alignSelf:"center",
        width:"60%"
    },
    name : {
        fontSize:25,
        marginBottom:5
    },
    priceArea : {
        marginVertical:25,
        fontSize:15
    },
    cancel : {
        backgroundColor : "#2196F3",
        marginTop:50,
        padding:10,
        borderRadius:3
    },
    cancelText : {
        color:"white",
        fontSize:15
    }
})
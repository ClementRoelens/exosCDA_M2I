import { Pressable, StyleSheet, Text, View } from 'react-native'
import React from 'react'

interface KeyInterface {
    keyName:string;
    isOperation:boolean;
    onKeyPress : (keyPressed:string) => void;
}

const Key = (props: KeyInterface) => {
    return (
        <Pressable 
            style={[styles.keyStyle, (props.isOperation) ? styles.operationKey : styles.otherKey]} 
            onPress={() => props.onKeyPress(props.keyName)}>
            <View>
                <Text style={styles.text}>{props.keyName}</Text>
            </View>
        </Pressable>
    )
}

export default Key

const styles = StyleSheet.create({
    keyStyle : {
        borderRadius : 10,
        width:80,
        height:80,
        justifyContent:"center",
        alignItems:"center",
        margin:10
    },
    otherKey : {
        backgroundColor:"#cccccc"
    },
    operationKey : {
        backgroundColor:"#636363"
    },
    text : {
        fontSize:40
    }
});
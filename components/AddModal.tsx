import { useState } from "react";
import { Button, Image, Modal, StyleSheet, Text, TextInput, View } from "react-native";
import { Article } from "../models/Article";

function AddModal(props: ModaleProps) {
    const [name, setName] = useState("");
    const [price,setPrice] = useState(0);
    const [quantity, setQuantity] = useState(0);

    const styles = StyleSheet.create({
        global: {
            flex: 1,
            justifyContent: "center"
        },
        input: {
            borderWidth: 1,
            borderColor: "rgba(150,150,150,0.5)",
            borderRadius: 12,
            marginHorizontal: 50,
            marginTop: 30,
            color: "gray"
        },
        inputActions: {
            flexDirection: "row",
            justifyContent: "space-around",
            marginTop: 15,
            marginHorizontal: 60
        },
        image: {
            width: 100,
            height: 100,
            alignSelf: "center",
            marginTop:0
        },
        inputElement : {
            width : "60%",
            justifyContent:"center",
            alignSelf:"center",
            marginBottom:15
        },
        textInput :  {
            textAlign:"center",
            marginBottom:0
        },
        inputArea :{
            marginVertical:25
        }
    });

    function addArticle(){
        props.addToCart(new Article(name,price,quantity));
    }

    return (
        <Modal visible={props.visible} animationType="fade">
            <View style={styles.global}>
                <Image style={styles.image} source={require("../assets/cart.png")} width={100} height={100} />
                <View style={styles.inputArea}>
                    <View style={styles.inputElement}>
                        <Text style={styles.textInput}>Nom : </Text>
                        <TextInput style={styles.input} onChangeText={e => setName(e)} />
                    </View>
                    <View style={styles.inputElement}>
                        <Text style={styles.textInput}>Prix : </Text>
                        <TextInput inputMode="numeric" style={styles.input} onChangeText={e => setPrice(+e)} />
                    </View>
                    <View style={styles.inputElement}>
                        <Text style={styles.textInput}>Quantité : </Text>
                        <TextInput inputMode="numeric" style={styles.input} onChangeText={e => setQuantity(+e)} />
                    </View>
                </View>
                <View style={styles.inputActions}>
                    <Button title="Ajouter article" onPress={addArticle} />
                    <Button title="Annnuler" color="red" onPress={() => props.close()} />
                </View>
            </View>
        </Modal>
    );
}

interface ModaleProps {
    visible: boolean;
    addToCart: (article: Article) => void;
    close: () => void;
}

export default AddModal;
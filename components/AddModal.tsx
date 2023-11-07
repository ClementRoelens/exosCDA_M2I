import { useState } from "react";
import { Button, Image, Modal, StyleSheet, TextInput, View } from "react-native";

function AddModal(props: ModaleProps) {
    const [inputText, setInputText] = useState("");

    const styles = StyleSheet.create({
        global: {
            flex:1,
            justifyContent:"center"
        },
        input: {
            borderWidth: 1,
            borderColor: "rgba(150,150,150,0.5)",
            borderRadius: 12,
            marginHorizontal: 50,
            marginTop: 30,
            color:"gray"
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
            alignSelf: "center"
        }
    });

    function updateText(e: string) {
        setInputText(e);
    }

    return (
        <Modal visible={props.visible}>
            <View style={styles.global}>
                <Image style={styles.image} source={require("../assets/cart.png")} width={100} height={100} />
                <TextInput style={styles.input} onChangeText={updateText}/>
                <View style={styles.inputActions}>
                    <Button title="Ajouter article" onPress={() => props.addToCart(inputText)} />
                    <Button title="Annnuler" color="red" onPress={() => props.close()} />
                </View>
            </View>
        </Modal>
    );
}

interface ModaleProps {
    visible: boolean;
    addToCart: (item: string) => void;
    close: () => void
}

export default AddModal;
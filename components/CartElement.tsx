import { Pressable, StyleSheet, Text, View } from "react-native";

function CartElement(props:CartElementInterface) {
    const styles = StyleSheet.create({
        cartItem: {
            backgroundColor: "#5e0acc",
            marginVertical: 5,
            marginHorizontal: 25,
            padding: 10,
            borderRadius: 5,
            color: "white"
        }
    });

    return (
        <Pressable onPress={props.deleteHandler}>
            <Text style={styles.cartItem}>{props.item}</Text>
        </Pressable>
    );
}

interface CartElementInterface {
    item:string;
    deleteHandler : () => void;
}

export default CartElement;
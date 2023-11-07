import { useState } from "react";
import { Button, FlatList, Image, StyleSheet, Text, TextInput, View } from "react-native";
import AddModal from "./AddModal";
import CartElement from "./CartElement";

function CartList() {
    const styles = StyleSheet.create({
        initialStyle: {
            marginTop: 15
        },
        list : {
            marginTop:15
        }
    });

    const [cart, setCart] = useState<string[]>([]);
    const [isAddMode, setIsAddMode] = useState(false);

    function addToCart(item: string) {
        if (item.trim() !== ""){
            setCart(prevCart => [...prevCart, item]);
        }
        setIsAddMode(false);
    }

    function deleteFromCart(index:number){
        setCart((prevCart:string[]) => {
            const newCart = [...prevCart];
            newCart.splice(index,1);
            return newCart;
        });
    }

    return (
        <View style={styles.initialStyle}>
            <Button title="Ajouter à la liste" onPress={() => setIsAddMode(true)} />
            <FlatList style={styles.list} data={cart} renderItem={(item) => {
                return <CartElement item={item.item} deleteHandler={() => deleteFromCart(item.index)}/>
            }} keyExtractor={(item, index) => {
                return index.toString();
            }}
            ></FlatList>
            <AddModal visible={isAddMode} addToCart={(item: string) => addToCart(item)} close={() => setIsAddMode(false)} />
            
        </View>
    );

}

export default CartList;
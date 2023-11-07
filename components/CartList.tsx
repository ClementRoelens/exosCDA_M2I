import { useState } from "react";
import { Button, FlatList, Image, StyleSheet, Text, TextInput, View } from "react-native";
import AddModal from "./AddModal";
import CartElement from "./CartElement";
import { Article } from "../models/Article";
import ArticleDetail from "./ArticleDetail";

function CartList() {
    const styles = StyleSheet.create({
        initialStyle: {
            marginTop: 15
        },
        list: {
            marginTop: 15
        }
    });

    const [cart, setCart] = useState<Article[]>([]);
    const [article,setArticle] = useState<Article | null>(null);
    const [isAddMode, setIsAddMode] = useState(false);
    const [isDetailMode,setIsDetailmode] = useState(false);

    function addToCart(article: Article) {
        setCart(prevCart => [...prevCart, article]);
        setIsAddMode(false);
    }

    function deleteFromCart(id: string) {
        setCart(prevCart => prevCart.filter((article: Article) => article.id !== id));
        setIsDetailmode(false);
        setArticle(null);
    }

    function showArticle(shownArticle:Article){
        setArticle(shownArticle);
        setIsDetailmode(true);
    }

    return (
        <View style={styles.initialStyle}>
            <Button title="Ajouter à la liste" onPress={() => setIsAddMode(true)} />
            <FlatList style={styles.list} data={cart} renderItem={(item) => {
                return <CartElement article={item.item} trigger={() => showArticle(item.item)} />
            }} keyExtractor={(item, index) => {
                return index.toString();
            }}
            ></FlatList>
            <AddModal visible={isAddMode} addToCart={(article: Article) => addToCart(article)} close={() => setIsAddMode(false)} />
            {article &&
                <ArticleDetail visible={isDetailMode} article={article!} deleteHandler={() => deleteFromCart(article!.id)} close={() => setIsDetailmode(false)}/>}
        </View>
    );

}

export default CartList;
import { Pressable, StyleSheet, Text } from "react-native";
import { Article } from "../models/Article";

function ArticleThumb(props: ArticleThumbInterface) {
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
        <Pressable onPress={props.trigger}>
            <Text style={styles.cartItem}>{props.article.name}</Text>
        </Pressable>
    );
}

interface ArticleThumbInterface {
    article: Article;
    trigger : () => void;
}

export default ArticleThumb;
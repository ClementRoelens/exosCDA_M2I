import { createContext } from "react";
import { Article } from "../models/Article";

const ArticleContext = createContext<ArticleContextInterface>({
    articles:[],
    setArticles : () => {}
});

interface ArticleContextInterface {
    articles:Article[];
    setArticles:React.Dispatch<React.SetStateAction<Article[]>>;
}

export default ArticleContext;
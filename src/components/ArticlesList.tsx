import ArticleContext from "../contexts/ArticleContext";
import { useContext } from "react";
import { Article } from "../models/Article";
import ArticleThumbnail from "./ArticleThumbnail";
import { useNavigate } from "react-router-dom";

function ArticlesList() {
    const { articles } = useContext(ArticleContext)
    const navigate = useNavigate();

    return (
        <ul className="flex flex-wrap justify-around">
            {articles.map((article: Article) =>
                <li className="w-3/12 mx-4 border-2 rounded-2xl my-2 cursor-pointer py-4" key={article.id} onClick={() => navigate("/detail/"+article.id)}><ArticleThumbnail article={article} /></li>
            )}
        </ul>
    );
}

export default ArticlesList;
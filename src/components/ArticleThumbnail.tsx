// import { useNavigate } from "react-router-dom";
import { Article } from "../models/Article";

function ArticleThumbnail(props: ArticleProps) {

    return (<>
        <h3 className="mx-auto text-center my-2">{props.article.name}</h3>
        <img className="h-48 mx-auto" src={props.article.imagePath} alt={props.article.name} />
    </>);
}

interface ArticleProps {
    article: Article;
}

export default ArticleThumbnail;
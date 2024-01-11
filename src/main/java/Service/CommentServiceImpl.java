package Service;

import DAO.CommentDAOImpl;
import DAO.ImageDAOImpl;
import DAO.ProductDAOImpl;
import Entities.Comment;
import Entities.Product;

import java.util.ArrayList;
import java.util.List;

public class CommentServiceImpl implements IService<Comment> {
    private static CommentServiceImpl instance = null;
    private static final Object lock = new Object();
    private CommentDAOImpl commentDAO;
    private ProductDAOImpl productDAO;

    private CommentServiceImpl(){
        commentDAO = new CommentDAOImpl();
        productDAO = new ProductDAOImpl();
    }

    public static CommentServiceImpl getInstance(){
        if (instance == null){
            synchronized (lock){
                instance = new CommentServiceImpl();
            }
        }
        return instance;
    }



    @Override
    public Comment post(Comment comment) {
        return commentDAO.create(comment);
    }

    @Override
    public List<Comment> getAll() {
        return commentDAO.read();
    }

    @Override
    public Comment getOne(int id) {
        return commentDAO.read(id);
    }

    public List<Comment> getAllAboutOneProduct(int productId){
        return commentDAO.readAllAboutOneProduct(productId);
    }
    
    @Override
    public boolean update(Comment comment) {
        return commentDAO.update(comment);
    }

    @Override
    public boolean delete(int id) {
        Comment comment = commentDAO.read(id);
        if (comment != null){
            return commentDAO.delete(comment);
        }
        return false;
    }

    @Override
    public void close() {
        commentDAO.close();
    }
}

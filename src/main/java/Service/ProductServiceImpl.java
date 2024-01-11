package Service;

import DAO.CommentDAOImpl;
import DAO.ProductDAOImpl;
import Entities.Comment;
import Entities.Product;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ProductServiceImpl implements IService<Product> {
    private static ProductServiceImpl instance = null;
    private static final Object lock = new Object();
    private ProductDAOImpl productDAO;
    private CommentDAOImpl commentDAO;

    private ProductServiceImpl(){
        productDAO = new ProductDAOImpl();
        commentDAO = new CommentDAOImpl();
    }

    public static ProductServiceImpl getInstance(){
        if (instance == null){
            synchronized (lock){
                instance = new ProductServiceImpl();
            }
        }
        return instance;
    }


    @Override
    public Product post(Product product){
        return productDAO.create(product);
    }
    @Override
    public List<Product> getAll(){
        return productDAO.read();
    }

    @Override
    public Product getOne(int id){
        return productDAO.read(id);
    }

    public List<Product> getProductsBetweenTwoDates(Date dateOne, Date dateTwo){
        return productDAO.readBetweenDates(dateOne,dateTwo);
    }

    public List<Product> getProductsWhereStockLowerThan(int stockLimit){
        return productDAO.readWhereStockLowerThan(stockLimit);
    }

    public double getTotalValueFromMark(String mark){
        return productDAO.readTotalValueFromMark(mark);
    }

    public List<Product> getProductsFromMark(String mark){
        return productDAO.readFromOneMark(mark);
    }

    public double getAveragePrice(){
        return productDAO.readAveragePrice();
    }

    public HashSet<Product> getProductsWithCommentRatingsHigherThan(int threshold){
        HashSet<Product> products = new HashSet<>();
        List<Comment> comments = commentDAO.readCommentsWithRatingHigherThan(threshold);

        for (Comment comment : comments){
            products.add(comment.getProduct());
        }

        return products;
    }

    @Override
    public boolean update(Product product){
        return productDAO.update(product);
    }

    @Override
    public boolean delete(int id){
        Product product = productDAO.read(id);
        if (product != null){
            return productDAO.delete(product);
        }
        return false;
    }

    public int deleteAllFromOneMark(String mark){
        return productDAO.deleteAllFromOneMark(mark);
    }

    @Override
    public void close(){
        productDAO.close();
    }
}

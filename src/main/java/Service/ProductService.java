package Service;

import DAO.ProductDAOImpl;
import Entities.Product;

import java.sql.Date;
import java.util.List;

public class ProductService {
    private static ProductService instance = null;
    private static Object lock = new Object();
    private ProductDAOImpl dao;

    private ProductService(){
        dao = ProductDAOImpl.getInstance();
    }

    public static ProductService getInstance(){
        if (instance == null){
            synchronized (lock){
                instance = new ProductService();
            }
        }
        return instance;
    }



    public Product postProduct(Product product){
        return dao.create(product);
    }

    public List<Product> getProducts(){
        return dao.read();
    }


    public Product getProduct(int id){
        return dao.read(id);
    }

    public List<Product> getProductsBetweenTwoDates(Date dateOne, Date dateTwo){
        return dao.readBetweenDates(dateOne,dateTwo);
    }

    public List<Product> getProductsWhereStockLowerThan(int stockLimit){
        return dao.readWhereStockLowerThan(stockLimit);
    }

    public double getTotalValueFromMark(String mark){
        return dao.readTotalValueFromMark(mark);
    }

    public List<Product> getProductsFromMark(String mark){
        return dao.readFromOneMark(mark);
    }

    public double getAveragePrice(){
        return dao.readAveragePrice();
    }

    public boolean updateProduct(Product product){
        return dao.update(product);
    }

    public boolean deleteProduct(int id){
        Product product = dao.read(id);
        if (product != null){
            return dao.delete(product);
        }
        return false;
    }

    public int deleteAllFromOneMark(String mark){
        return dao.deleteAllFromOneMark(mark);
    }

    public void close(){
        dao.close();
    }
}

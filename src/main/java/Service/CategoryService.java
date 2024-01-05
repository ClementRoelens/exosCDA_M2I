package Service;

import DAO.CategoryDAOImpl;
import Entity.Category;

import java.util.List;

public class CategoryService {
    private CategoryDAOImpl categoryDAO = new CategoryDAOImpl();

    public Category postCategory(Category category){
        return categoryDAO.create(category);
    }

    public List<Category> getCategories(){
        return categoryDAO.read();
    }

    public Category getCategorie(int id){
        return categoryDAO.read(id);
    }

    public boolean updateCategory(Category category){
        return categoryDAO.update(category);
    }

    public boolean deleteCategory(int id){
        return categoryDAO.delete(id);
    }
}

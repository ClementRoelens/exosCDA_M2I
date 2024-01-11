package Service;

import DAO.ImageDAOImpl;
import Entities.Image;

import java.util.List;

public class ImageServiceImpl implements IService<Image> {
    private static ImageServiceImpl instance = null;
    private static final Object lock = new Object();
    private ImageDAOImpl dao;

    private ImageServiceImpl(){
        dao = new ImageDAOImpl();
    }

    public static ImageServiceImpl getInstance(){
        if (instance == null){
            synchronized (lock){
                instance = new ImageServiceImpl();
            }
        }
        return instance;
    }


    @Override
    public Image post(Image image) {
        return dao.create(image);
    }

    @Override
    public List<Image> getAll() {
        return dao.read();
    }

    @Override
    public Image getOne(int id) {
        return dao.read(id);
    }

    @Override
    public boolean update(Image image) {
        return dao.update(image);
    }

    @Override
    public boolean delete(int id) {
        Image image = dao.read(id);
        if (image != null){
            return dao.delete(image);
        }
        return false;
    }

    @Override
    public void close() {
        dao.close();
    }
}

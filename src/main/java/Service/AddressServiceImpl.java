package Service;

import DAO.AddressDAOImpl;
import DAO.CommandDAOImpl;
import Entities.Address;

import java.util.List;

public class AddressServiceImpl implements IService<Address> {
    private static AddressServiceImpl instance = null;
    private static final Object lock = new Object();
    private AddressDAOImpl addressDAO;


    private AddressServiceImpl(){
        addressDAO = new AddressDAOImpl();
    }

    public static AddressServiceImpl getInstance(){
        if (instance == null){
            synchronized (lock){
                instance = new AddressServiceImpl();
            }
        }
        return instance;
    }



    @Override
    public Address post(Address address) {
        return addressDAO.create(address);
    }

    @Override
    public List<Address> getAll() {
        return addressDAO.read();
    }

    @Override
    public Address getOne(int id) {
        return addressDAO.read(id);
    }

    @Override
    public boolean update(Address address) {
        return addressDAO.update(address);
    }

    @Override
    public boolean delete(int id) {
        Address address = addressDAO.read(id);
        if (address != null){
            return addressDAO.delete(address);
        }
        return false;
    }

    @Override
    public void close() {
        addressDAO.close();
    }
}

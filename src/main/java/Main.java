import Entities.Product;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import java.sql.Date;
import java.util.List;

public class Main {
    public static void createProducts(){
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Product[] products = {
                new Product("Soda", "Coca-Cola", Date.valueOf("2024-01-01"), 1.50, 100),
                new Product("Smartphone", "Samsung", Date.valueOf("2019-12-20"), 799.99, 50),
                new Product("Chaussures de sport", "Nike", Date.valueOf("2022-07-05"), 99.99, 30),
                new Product("Téléviseur", "Sony", Date.valueOf("2017-05-11"), 299.99, 20),
                new Product("Dentifrice", "Colgate", Date.valueOf("2024-01-04"), 2.99, 200)
        };

        System.out.println("Produits");
        for (Product p : products){
            session.save(p);
        }

        session.getTransaction().commit();
        session.close();
    }


    public static void main(String[] args) {
        createProducts();

        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Product p1 = session.load(Product.class,2);
        System.out.println(p1);

        Product p2 = session.load(Product.class,3);
        session.remove(p2);

        Product p3 = session.load(Product.class,1);
        p3.setMark("Marque fictive");
        session.update(p3);

        session.getTransaction().commit();


        List<Product> products = session.createQuery("FROM Product").list();
        System.out.println("\nTous les produits: \n");
        for (Product p : products){
            System.out.println(p);
        }

        Query<Product> query = session.createQuery("FROM Product WHERE price > :price");
        query.setParameter("price",100D);
        products = query.list();
        System.out.println("\nTous les produits de plus de 100€: \n");
        for (Product p : products){
            System.out.println(p);
        }

        Date date1 = Date.valueOf("2020-01-01");
        Date date2 = Date.valueOf("2024-01-07");
        query = session.createQuery("FROM Product WHERE buyDate > ?1 AND buyDate < ?2");
        query.setParameter(1, date1);
        query.setParameter(2, date2);
        products = query.list();
        System.out.printf("\nTous les produits achetés entre le %s et le %s\n\n", date1, date2);
        for (Product p : products){
            System.out.println(p);
        }
    }
}

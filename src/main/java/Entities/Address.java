package Entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int number;
    private String road;
    private String town;
    @Column(length = 5, name = "zip_code")
    private String zipCode;
    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    private List<Command> commands;


    public Address() {
    }

    public Address(int number, String road, String zipCode,String town) {
        this.number = number;
        this.road = road;
        this.zipCode = zipCode;
        this.town = town;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }


    @Override
    public String toString() {
        return String.format("Adresse n°%d - %d %s - %s %s",
                id, number, road, zipCode, town);
    }
}

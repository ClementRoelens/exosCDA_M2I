package org.example.entity;

public class Car {
    private int rentDueIn;
    private int condition;
    private String type;
    private boolean isOld;


    public Car() {
        rentDueIn = 2;
        condition = 50;
        type = "default";
        isOld = false;
    }



    public int getRentDueIn() {
        return rentDueIn;
    }

    public void setRentDueIn(int rentDueIn) {
        this.rentDueIn = rentDueIn;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        if (condition < 0){
            this.condition = 0;
        } else if (condition > 100){
            this.condition = 100;
        } else {
            this.condition = condition;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOld() {
        return isOld;
    }

    public void setOld(boolean old) {
        isOld = old;
    }

    public void dailyRoutine(){
        rentDueIn--;
        if (!type.equals("luxuary")){
            int decrementation = (rentDueIn == 0) ? 2 : 1;
            if (isOld){
                setCondition(condition - decrementation*2);
            } else {
                setCondition(condition - decrementation);
            }
        } else {
            setCondition(condition+1);
        }
    }

}

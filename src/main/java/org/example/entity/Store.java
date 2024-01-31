package org.example.entity;

public class Store {
    private int sellIn;
    private int quality;
    private String name;
    private String type;


    public Store() {
        name = "default";
        quality = 5;
        sellIn = 2;
        type = "default";
    }

    public void dailyUpdate(){
        sellIn--;
        if (name.equals("Old brie")){
            setQuality(quality + 1);
        } else {
            if (quality != 0){
                int decrementation = (type.equals("Dairy")) ? 2 : 1;
                if (sellIn > 0){
                    quality -= decrementation;
                } else {
                    quality -= 2*decrementation;
                }
            }
        }
    }

    public int getSellIn() {
        return sellIn;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        quality = Math.min(quality, 50);
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

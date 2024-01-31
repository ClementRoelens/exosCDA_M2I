package entity;

import org.example.entity.Store;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StoreTest {

    private Store store;

    @BeforeEach
    void setup(){
        store = new Store();
    }

    @Test
    void whenSellInIsGreaterThanZeroThenQualityIsDecrementedByOne() {
        int qualityAtTwo = store.getQuality();
        store.dailyUpdate();
        int qualityAtOne = store.getQuality();
        int result = qualityAtTwo - qualityAtOne;
        Assertions.assertEquals(1, result);
    }

    @Test
    void whenSellInEqualsToZeroThenQualityIsDecrementedByTwo(){
        store.setSellIn(1);
        int qualityAtOne = store.getQuality();
        store.dailyUpdate();
        int qualityAtZero = store.getQuality();
        int result = qualityAtOne - qualityAtZero;
        Assertions.assertEquals(2, result);
    }

    @Test
    void whenQualityIsEqualsToZeroThenDailyUpdateIsNotDecremented(){
        store.setQuality(0);
        store.dailyUpdate();
        int result = store.getQuality();
        Assertions.assertEquals(0,result);
    }

    @Test
    void whenTryingToSetQualityGreaterThan50ThenQualityStaysAt50(){
        store.setQuality(5000);
        int result = store.getQuality();
        Assertions.assertEquals(50,result);
    }

    @Test
    void whenOldBrieGetUpdatedThenQualityIncreases(){
        store.setName("Old brie");
        store.setQuality(1);
        store.dailyUpdate();
        int result = store.getQuality();
        Assertions.assertEquals(2, result);
    }

    @Test
    void whenDairyTypeIsUpdatedAtSellin2ThenQualityIsDecreasedByTwo(){
        store.setType("Dairy");
        int qualityAtTwo = store.getQuality();
        store.dailyUpdate();
        int qualityAtOne = store.getQuality();
        int result = qualityAtTwo - qualityAtOne;
        Assertions.assertEquals(2, result);
    }

    @Test
    void whenDairyTypeIsUpdatedAtSellin1ThenQualityIsDecreasedByFour(){
        store.setType("Dairy");
        store.setSellIn(1);
        int qualityAtOne = store.getQuality();
        store.dailyUpdate();
        int qualityAtZero = store.getQuality();
        int result = qualityAtOne - qualityAtZero;
        Assertions.assertEquals(4, result);
    }
}


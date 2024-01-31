package entity;

import org.example.entity.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarRentTest {
    private Car car;

    @BeforeEach
    void setup(){
        car = new Car();
    }

    @Test
    void whenRentDuelInIsGreaterThanZeroAndDailyRoutineThenConditionisDecreasedByOne(){
        int conditionAtTwo = car.getCondition();
        car.dailyRoutine();
        int conditionAtOne = car.getCondition();
        int result = conditionAtTwo - conditionAtOne;
        Assertions.assertEquals(1,result);
    }

    @Test
    void whenRentDuelInIsEqualsToZeroAndDailyRoutineThenConditionIsDecreasedByTwo(){
        car.setRentDueIn(1);
        int conditionAtOne = car.getCondition();
        car.dailyRoutine();
        int conditionAtZero = car.getCondition();
        int result = conditionAtOne - conditionAtZero;
        Assertions.assertEquals(2,result);
    }

    @Test
    void whenConditionIsSetLowerThanZeroThenConditionFloorsToZero(){
        car.setCondition(-50);
        int result = car.getCondition();
        Assertions.assertEquals(0,result);
    }

    @Test
    void whenConditionIsSetGreaterThanOneHundredThenConditionCeilsToOneHundred(){
        car.setCondition(150);
        int result = car.getCondition();
        Assertions.assertEquals(100,result);
    }

    @Test
    void whenTypeIsLuxuaryAndDailyRoutineThenConditionIsIncresed(){
        car.setType("luxuary");
        int conditionAtTwo = car.getCondition();
        car.dailyRoutine();
        int conditionAtOne = car.getCondition();
        int result = conditionAtTwo-conditionAtOne;
        Assertions.assertEquals(-1,result);
    }

    @Test
    void whenIsOldAndDailyRoutineThenConditionIsDecreasedTwoTimes(){
        car.setOld(true);
        int conditionAtTwo = car.getCondition();
        car.dailyRoutine();
        int conditionAtOne = car.getCondition();
        int result = conditionAtTwo-conditionAtOne;
        Assertions.assertEquals(2,result);
    }
}

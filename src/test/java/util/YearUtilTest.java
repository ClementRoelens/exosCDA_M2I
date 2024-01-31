package util;

import org.example.util.YearUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class YearUtilTest {

    private int year = 2008;

    @Test
    void isDivisibledBy400() {
        boolean result = YearUtil.isLeap(year);
        Assertions.assertEquals(result, year%400 == 0);
    }

    @Test
    void isDivisibledBy4(){
        boolean result = YearUtil.isLeap(year);
        Assertions.assertEquals(result,year%4 == 0);
    }

       @Test
    void isNotDivisibledBy100(){
        boolean result = YearUtil.isLeap(year);
        Assertions.assertTrue(year%100 == 0);
    }

       @Test
    void isDivisibledBy4000(){
        boolean result = YearUtil.isLeap(year);
        Assertions.assertTrue(year%4000 == 0);
    }


}

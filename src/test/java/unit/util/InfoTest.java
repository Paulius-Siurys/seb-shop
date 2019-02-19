package unit.util;

import org.junit.Test;
import org.uptown.shop.util.info.NumericInfo;
import org.uptown.shop.util.info.NumericOption;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class InfoTest {

    @Test
    public void constructor_success() {
        NumericOption incomeOption0 = new NumericOption(42, "0", 0);
        NumericOption incomeOption1 = new NumericOption(43, "1-1000", 1);
        NumericInfo incomeInfo = new NumericInfo(42, "Income", Arrays.asList(incomeOption0, incomeOption1));
        NumericInfo ageInfo = new NumericInfo(43, "Age", null);
        assertEquals(incomeInfo, incomeOption0.getInfo());
        assertEquals(incomeInfo, incomeOption1.getInfo());
    }

    @Test(expected = RuntimeException.class)
    public void constructor_sameIds_throwsRuntimeException() {
        NumericInfo incomeInfo = new NumericInfo(42, "Income", null);
        NumericInfo ageInfo = new NumericInfo(42, "Age", null);
    }
}

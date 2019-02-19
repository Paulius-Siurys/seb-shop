package unit.util;

import org.junit.Test;
import org.uptown.shop.util.info.BooleanOption;

public class OptionTest {

    @Test
    public void constructor_success() {
        BooleanOption optionYes = new BooleanOption(72, "Yes");
        BooleanOption optionNo = new BooleanOption(73, "No");
    }

    @Test(expected = RuntimeException.class)
    public void constructor_sameIds_throwsRuntimeException() {
        BooleanOption optionYes = new BooleanOption(72, "Yes");
        BooleanOption optionNo = new BooleanOption(72, "No");
    }
}

package unit.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.uptown.shop.config.Configuration;
import org.uptown.shop.service.impl.ProductServiceImpl;
import org.uptown.shop.util.info.BooleanInfo;
import org.uptown.shop.util.info.BooleanOption;
import org.uptown.shop.util.info.NumericInfo;
import org.uptown.shop.util.info.NumericOption;
import org.uptown.shop.util.product.Product;
import org.uptown.shop.util.product.ProductRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {

    @Mock
    private Configuration config;

    private ProductServiceImpl productServiceImpl;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        productServiceImpl = new ProductServiceImpl(config);
    }

    @Test
    public void list_success() {
        List<Integer> selectedOptionIds = new ArrayList<>();
        selectedOptionIds.add(32);
        selectedOptionIds.add(null);
        selectedOptionIds.add(34);
        NumericOption incomeOption0 = new NumericOption(32, "0", 0);
        NumericOption incomeOption1 = new NumericOption(33, "1-1000", 1);
        NumericInfo incomeInfo = new NumericInfo(32, "Income", Arrays.asList(incomeOption0, incomeOption1));
        BooleanOption studentOptionYes = new BooleanOption(34, "Yes");
        BooleanOption studentOptionNo = new BooleanOption(35, "No");
        BooleanInfo studentInfo = new BooleanInfo(33, "Student", Arrays.asList(studentOptionYes, studentOptionNo));

        when(config.getOptionList())
                .thenReturn(Arrays.asList(incomeOption0, incomeOption1, studentOptionYes, studentOptionNo));

        Product creditCardProduct = mock(Product.class);
        Product debitCardProduct = mock(Product.class);
        when(config.getProductList())
                .thenReturn(Arrays.asList(creditCardProduct, debitCardProduct));

        ProductRule creditCardProductRule = mock(ProductRule.class);
        when(creditCardProduct.getRule())
                .thenReturn(creditCardProductRule);
        when(creditCardProductRule.test(Arrays.asList(incomeOption0, studentOptionYes)))
                .thenReturn(true);

        ProductRule debitCardProductRule = mock(ProductRule.class);
        when(debitCardProduct.getRule())
                .thenReturn(debitCardProductRule);
        when(debitCardProductRule.test(Arrays.asList(incomeOption0, studentOptionYes)))
                .thenReturn(false);

        List<Product> actualProducts = productServiceImpl.list(selectedOptionIds);
        assertEquals(actualProducts, Arrays.asList(creditCardProduct));
    }

    @Test(expected = RuntimeException.class)
    public void list_optionIdDoesNotExists_throwsRuntimeException() {
        List<Integer> selectedOptionIds = new ArrayList<>();
        selectedOptionIds.add(52);
        selectedOptionIds.add(54);
        NumericOption incomeOption0 = new NumericOption(52, "0", 0);

        when(config.getOptionList())
                .thenReturn(Arrays.asList(incomeOption0));

        productServiceImpl.list(selectedOptionIds);
    }

    @Test(expected = RuntimeException.class)
    public void list_selectTwoOptionsWithSameInfo_throwsRuntimeException() {
        List<Integer> selectedOptionIds = new ArrayList<>();
        selectedOptionIds.add(62);
        selectedOptionIds.add(63);
        NumericOption incomeOption0 = new NumericOption(62, "0", 0);
        NumericOption incomeOption1 = new NumericOption(63, "1-1000", 1);
        NumericInfo incomeInfo = new NumericInfo(62, "Income", Arrays.asList(incomeOption0, incomeOption1));
        when(config.getOptionList())
                .thenReturn(Arrays.asList(incomeOption0, incomeOption1));

        productServiceImpl.list(selectedOptionIds);
    }
}

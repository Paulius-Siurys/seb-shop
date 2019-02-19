package unit.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.uptown.shop.Application;
import org.uptown.shop.controller.ProductController;
import org.uptown.shop.service.ProductService;
import org.uptown.shop.util.product.Product;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@ContextConfiguration(classes={Application.class})
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void list_success() throws Exception {
        List<Integer> options = Arrays.asList(42, 43, 45);
        when(productService.list(options))
                .thenReturn(Arrays.asList(
                        new Product("Debit Card", null),
                        new Product("Credit Card", null)));
        String optionsParam = options.toString();
        optionsParam = optionsParam.substring(1, optionsParam.length() - 1).replaceAll("\\s","");
        this.mockMvc.perform(get("/products")
                .param("options", optionsParam))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{\"name\":\"Debit Card\"}," +
                                "{\"name\":\"Credit Card\"}]"));
    }
}

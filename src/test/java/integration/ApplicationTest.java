package integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.uptown.shop.Application;
import org.uptown.shop.util.product.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})
@AutoConfigureMockMvc
public class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void list_success() throws Exception {
        MvcResult infosResult = this.mockMvc.perform(get("/infos"))
                .andExpect(status().isOk())
                .andReturn();

        JSONArray infos = new JSONArray(infosResult.getResponse().getContentAsString());
        List<Integer> options = new ArrayList<>();
        for (int i = 0; i < infos.length(); i++) {
            JSONObject info = infos.getJSONObject(i);
            options.add(info.getJSONArray("options").getJSONObject(0).getInt("id"));
        }

        String optionsParam = options.toString();
        optionsParam = optionsParam.substring(1, optionsParam.length() - 1).replaceAll("\\s","");
        MvcResult productsResult = this.mockMvc.perform(get("/products")
                .param("options", optionsParam))
                .andExpect(status().isOk())
                .andReturn();

        List<Product> products = objectMapper.readValue(productsResult.getResponse().getContentAsString(),
                new TypeReference<List<Product>>() {});
        assertEquals("Junior Saver Account", products.get(0).getName());
    }
}

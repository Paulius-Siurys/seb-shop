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
import org.uptown.shop.controller.InfoController;
import org.uptown.shop.service.InfoService;
import org.uptown.shop.util.info.BooleanInfo;
import org.uptown.shop.util.info.NumericInfo;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InfoController.class)
@ContextConfiguration(classes={Application.class})
public class InfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InfoService infoService;

    @Test
    public void list_success() throws Exception {
        when(infoService.list())
                .thenReturn(Arrays.asList(
                        new NumericInfo(12, "Income", null),
                        new BooleanInfo(13, "Student", null)));
        this.mockMvc.perform(get("/infos"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{\"id\":12,\"name\":\"Income\",\"options\":null}," +
                                "{\"id\":13,\"name\":\"Student\",\"options\":null}]"));
    }
}

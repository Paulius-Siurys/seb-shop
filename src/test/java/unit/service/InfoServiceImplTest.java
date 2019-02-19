package unit.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.uptown.shop.config.Configuration;
import org.uptown.shop.service.impl.InfoServiceImpl;
import org.uptown.shop.util.info.BooleanInfo;
import org.uptown.shop.util.info.Info;
import org.uptown.shop.util.info.NumericInfo;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class InfoServiceImplTest {

    @Mock
    private Configuration config;

    private InfoServiceImpl infoServiceImpl;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        infoServiceImpl = new InfoServiceImpl(config);
    }

    @Test
    public void list_success() {
        List<Info> expectedInfos = Arrays.asList(
                new NumericInfo(22, "Income", null),
                new BooleanInfo(23, "Student", null));
        when(config.getInfoList())
                .thenReturn(expectedInfos);
        List<Info> actualInfos = infoServiceImpl.list();
        assertEquals(expectedInfos, actualInfos);
    }
}

package com.ws.springcloud.auth.server.controller;

import com.ws.springcloud.auth.server.AuthServerTestApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Description:
 *
 * @author 王松
 * @version 1.0
 * @date 19-1-8 上午10:05
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AuthServerTestApplication.class)
public class AbstractControllerTest {

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setupMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

}

package com.ws.springcloud.auth.server.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Description:
 *
 * @author 王松
 * @version 1.0
 * @date 19-1-8 上午10:08
 */
public class TestControllerTest extends AbstractControllerTest {

    @Test
    public void listTest() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/test/config"))
                .andExpect(jsonPath("commonKey").value("commonValue"))
                //一共有两个适配器
                .andExpect(jsonPath("key").value("devValue"));
        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
    }

}

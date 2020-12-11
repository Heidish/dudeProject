package com.dude.dudeproject.controller;

import com.dude.dudeproject.Controller.userController;
import com.dude.dudeproject.Service.userDaoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for {@link userController}
 *
 * @author dude
 */
@RunWith(SpringRunner.class)
@WebMvcTest(userController.class)
public class userControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    userDaoService service;

    @Test
    void testLoginPage() throws Exception {
        mockMvc.perform(get("/user/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("signup/login"));
    }

    @Test
    void testPreRegPage() throws Exception {
        mockMvc.perform(get("/user/preRegistration")).andExpect(status().isOk()).andExpect(model().attributeExists("user"))
                .andExpect(view().name("signup/preRegistration"));
    }

    @Test
    void testRegPage() throws Exception {
        mockMvc.perform(get("/user/registration")).andExpect(status().isOk()).andExpect(model().attributeExists("user"))
                .andExpect(view().name("signup/registration"));
    }

//    @Test
//    void testCreateUser() throws Exception {
//        mockMvc.perform(post("/user/add").param("user_no", "10000").param("user_name", "김두드")
//                .param("user_id", "dudekim").param("user_pw", "1234").param("user_mobile", "01012341234")
//                .param("user_address_gu", "마포구").param("user_address_dong", "대흥동")
//                .param("user_address_street", "십이다시이십번지").param("user_address_city", "서울특별시"))
//                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/signup/login"));
//    }

}
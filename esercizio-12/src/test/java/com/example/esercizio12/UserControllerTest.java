package com.example.esercizio12;

import com.example.esercizio12.controllers.UserController;
import com.example.esercizio12.entities.User;
import com.example.esercizio12.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void createUser() throws Exception {
        User user = new User();
        user.setName("Anto");
        user.setSurname("Troiano");
        user.setAge(30);
        user.setEmail("anto@gmail.com");

        userService.createUser(user);
        String userJSON = objectMapper.writeValueAsString(user);

        this.mockMvc.perform(post("/v1/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void readUsers() throws Exception {
        userService.createUser(new User(1, "anto", "anto",30,"anto@gmail"));
        userService.createUser(new User(2, "mario", "anto",29,"mario@gmail"));


        this.mockMvc.perform(get("/v1/users/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();


        assertThat(userService.getAll().size()).isNotZero();
        assertThat(userService.getAll()).contains(new User(1, "anto", "anto",30,"anto@gmail"));
        assertThat(userService.getAll()).contains(new User(2, "mario", "anto",29,"mario@gmail"));
    }

    @Test
    void updateUser() throws Exception {
        userService.createUser(new User(1, "anto", "anto",30,"anto@gmail"));

        this.mockMvc.perform(MockMvcRequestBuilders.patch("/v1/users/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                .content("{\"age\": 25}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void udeleteUser() throws Exception {
        userService.createUser(new User(1, "anto", "anto",30,"anto@gmail"));

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/v1/users/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}


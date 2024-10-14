package com.example.templateProject.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.templateProject.client.PersonsClient;
import com.example.templateProject.entity.User;
import com.example.templateProject.producer.KafkaProducer;
import com.example.templateProject.service.CoreService;
import com.example.templateProject.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {HomeController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class HomeControllerDiffblueTest {
    @MockBean
    private CoreService coreService;

    @MockBean
    private EmailService emailService;

    @Autowired
    private HomeController homeController;

    @MockBean
    private PersonsClient personsClient;

//    @MockBean
//    private KafkaProducer kafkaProducer;

    /**
     * Method under test: {@link HomeController#sendMail()}
     */
    @Test
    void testSendMail() throws Exception {
        // Arrange
        doNothing().when(emailService).sendMail();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/home/sendMail");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Sent mail successfully"));
    }

    /**
     * Method under test: {@link HomeController#sendMail()}
     */
    @Test
    void testSendMail2() throws Exception {
        // Arrange
        doNothing().when(emailService).sendMail();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/home/sendMail");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Sent mail successfully"));
    }

    /**
     * Method under test: {@link HomeController#getUserById(String)}
     */
    @Test
    void testGetUserById() throws Exception {
        // Arrange
        when(coreService.getUserById(Mockito.<String>any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/home/getUser/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link HomeController#addUser(User)}
     */
    @Test
    void testAddUser() throws Exception {
        // Arrange
        when(coreService.addUser(Mockito.<User>any())).thenReturn("Add User");

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId("42");
        user.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/home/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Add User"));
    }

    /**
     * Method under test: {@link HomeController#add(int, int)}
     */
    @Test
    void testAdd() throws Exception {
        // Arrange
        when(coreService.add(anyInt(), anyInt())).thenReturn(2);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/home/add");
        MockHttpServletRequestBuilder paramResult = getResult.param("x", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("y", String.valueOf(1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("2"));
    }

    /**
     * Method under test: {@link HomeController#sub(int, int)}
     */
    @Test
    void testSub() throws Exception {
        // Arrange
        when(coreService.sub(anyInt(), anyInt())).thenReturn(1);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/home/sub");
        MockHttpServletRequestBuilder paramResult = getResult.param("x", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("y", String.valueOf(1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    /**
     * Method under test: {@link HomeController#mul(int, int)}
     */
    @Test
    void testMul() throws Exception {
        // Arrange
        when(coreService.mul(anyInt(), anyInt())).thenReturn(1);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/home/mul");
        MockHttpServletRequestBuilder paramResult = getResult.param("x", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("y", String.valueOf(1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    /**
     * Method under test: {@link HomeController#div(int, int)}
     */
    @Test
    void testDiv() throws Exception {
        // Arrange
        when(coreService.div(anyInt(), anyInt())).thenReturn(1);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/home/div");
        MockHttpServletRequestBuilder paramResult = getResult.param("x", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("y", String.valueOf(1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    /**
     * Method under test: {@link HomeController#addUser(String)}
     */
    @Test
    void testAddUser2() throws Exception {
        // Arrange
        doNothing().when(coreService).removeUser(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/home/deleteUser/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("User is removed successfully"));
    }

    /**
     * Method under test: {@link HomeController#addUser(String)}
     */
    @Test
    void testAddUser3() throws Exception {
        // Arrange
        doNothing().when(coreService).removeUser(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/home/deleteUser/{id}", "42");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("User is removed successfully"));
    }

    /**
     * Method under test: {@link HomeController#getPersons()}
     */
    @Test
    void testGetPersons() throws Exception {
        // Arrange
        when(personsClient.getRandomPersons()).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/home/getPersons");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link HomeController#getUsers()}
     */
    @Test
    void testGetUsers() throws Exception {
        // Arrange
        when(coreService.getAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/home/users");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link HomeController#getUsers()}
     */
    @Test
    void testGetUsers2() throws Exception {
        // Arrange
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId("42");
        user.setName("Recieved a request to get all users from mongodb");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(coreService.getAllUsers()).thenReturn(userList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/home/users");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":\"42\",\"name\":\"Recieved a request to get all users from mongodb\",\"email\":\"jane.doe@example"
                                        + ".org\"}]"));
    }
}

package com.glb.bootcamp;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Optional;

import com.glb.bootcamp.endpoint.UsersEndpoint;
import com.glb.bootcamp.model.Routine;
import com.glb.bootcamp.model.User;
import com.glb.bootcamp.service.UsersRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UsersEndpoint.class)
public class UsersEndPointTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersRepository usersRepository;
    @MockBean
    private RestTemplateBuilder restTemplateBuilder;

    @Before
    public void setup() {
        Mockito.when(usersRepository.findById(Mockito.anyString())).thenReturn(Optional.of(mockUser));
        //Mockito.when(restTemplateBuilder.build().getForEntity(Mockito.anyString(),Routine.class,Mockito.anyInt())).thenReturn(new ResponseEntity<Routine>(routine, HttpStatus.OK));
    }

    private int[] routines = {2,4};
    private User mockUser = new User("dalvarez_sw", "Diego", 23, routines);
    private String[] ejercicios = {"Joel Specific Exercise","Lean Planche","Pseudo Push Ups"};
    private Routine routine = new Routine(2, "Straddle Planche Routine", "dalvarez_sw", ejercicios);

    @Test
    public void findUserByUsernameTest() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users/dalvarez_sw")
                .accept(MediaType.APPLICATION_JSON);
        
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());

        String expected = "{\"username\":\"dalvarez_sw\",\"name\":\"Diego\",\"edad\":23,\"routines\":[2,4]}";
        String response = result.getResponse().getContentAsString();

        assertEquals(expected, response);
    }

    // @Test
    // public void newUserTest() throws Exception {

    //     RequestBuilder requestBuilder = MockMvcRequestBuilders
    //             .post("/users")
    //             .accept(MediaType.APPLICATION_JSON);
        
    //     MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    //     String expected = "{\"id\":2,\"name\":\"Straddle Planche Routine\",\"creator\":\"dalvarez_sw\",\"ejercicios\":[\"Joel Specific Exercise\",\"Lean Planche\",\"Pseudo Push Ups\"]}";
    //     String response = result.getResponse().getContentAsString();

    //     assertEquals(expected, response);
    // }
    
}

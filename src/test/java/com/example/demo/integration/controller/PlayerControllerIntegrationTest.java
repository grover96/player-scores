package com.example.demo.integration.controller;

import com.example.demo.controller.PlayerController;
import com.example.demo.domain.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerControllerIntegrationTest {

    private MockMvc mvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private PlayerController playerController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        this.mvc = standaloneSetup(this.playerController).build();
    }

    @Test
    public void testGetPlayerInformationById_thenVerifyResponse() throws Exception {
        mvc.perform(get("/api/players/{id}", 1).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Michael Jordan")))
                .andExpect(jsonPath("$.team", is("Chicago Bulls")))
                .andExpect(jsonPath("$.numberOfMatchesPlayed", is(80)))
                .andExpect(jsonPath("$.averageScore", is(29.9)))
                .andReturn();
    }

    @Test
    public void testGetAllPlayers_thenVerifyResponse() throws Exception {
        mvc.perform(get("/api/players").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Michael Jordan")))
                .andExpect(jsonPath("$[0].team", is("Chicago Bulls")))
                .andExpect(jsonPath("$[0].numberOfMatchesPlayed", is(80)))
                .andExpect(jsonPath("$[0].averageScore", is(29.9)))
                .andExpect(jsonPath("$[1].name", is("Kobe Bryant")))
                .andExpect(jsonPath("$[1].team", is("Los Angeles Lakers")))
                .andExpect(jsonPath("$[1].numberOfMatchesPlayed", is(78)))
                .andExpect(jsonPath("$[1].averageScore", is(25.4)))
                .andExpect(jsonPath("$[2].name", is("Steph Curry")))
                .andExpect(jsonPath("$[2].team", is("Golden State Warriors")))
                .andExpect(jsonPath("$[2].numberOfMatchesPlayed", is(82)))
                .andExpect(jsonPath("$[2].averageScore", is(32.8)))
                .andReturn();
    }

    @Test
    public void testAddPlayer_andDeletePlayer_thenVerifyResponse() throws Exception {
        Player player = new Player(4, "Kyrie Irving", "Boston Celtics", 80, 25.6f);
        String content = objectMapper.writeValueAsString(player);

        mvc.perform(post("/api/players").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(content))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Kyrie Irving")))
                .andExpect(jsonPath("$.team", is("Boston Celtics")))
                .andExpect(jsonPath("$.numberOfMatchesPlayed", is(80)))
                .andExpect(jsonPath("$.averageScore", is(25.6)))
                .andReturn();

        mvc.perform(delete("/api/players/{id}", 4).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

    }
}

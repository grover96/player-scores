package com.example.demo.controller;

import com.example.demo.domain.Player;
import com.example.demo.services.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PlayerController.class)
public class PlayerAPITest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PlayerService playerService;

    ObjectMapper objectMapper = new ObjectMapper();
    Set<Player> players = new HashSet<>();
    Player player1;
    Player player2;
    Player player3;

    @Before
    public void setup() {

        player1 = new Player(1, "Michael Jordan", "Chicago Bulls", 80, 29.9f);
        player2 = new Player(2, "Kobe Bryant", "Los Angeles Lakers", 78, 25.4f);
        player3 = new Player(3, "Steph Curry", "Golden State Warriors", 82, 32.8f);

        players.add(player1);
        players.add(player2);
        players.add(player3);
    }

    @Test
    public void getAllPlayersTest() throws Exception {
        String expected = objectMapper.writeValueAsString(players);

        Mockito.when(playerService.getAllPlayers()).thenReturn(players);
        MvcResult result = mvc.perform(get("/api/players").accept(MediaType.APPLICATION_JSON)).andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo(expected);
    }

    @Test
    public void getPlayerByIdTest() throws Exception {
        String expected = objectMapper.writeValueAsString(player2);

        Mockito.when(playerService.getPlayerById(Mockito.anyLong())).thenReturn(player2);
        MvcResult result = mvc.perform(get("/api/players/{id}", 2).accept(MediaType.APPLICATION_JSON)).andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo(expected);
    }

    @Test
    public void addPlayerTest() throws Exception {
        Player player4 = new Player(4, "Ben Simmons", "Philadelphia Sixers", 80, 20.6f);
        String jsonPlayerObj = objectMapper.writeValueAsString(player4);

        Mockito.when(playerService.addPlayer(Mockito.any(Player.class))).thenReturn(player4);
        MvcResult result = mvc.perform(post("/api/players").accept(MediaType.APPLICATION_JSON).content(jsonPlayerObj).contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
        assertThat(result.getResponse().getContentAsString()).contains("Ben Simmons");
    }
}

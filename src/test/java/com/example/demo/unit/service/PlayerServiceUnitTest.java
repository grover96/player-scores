package com.example.demo.unit.service;

import com.example.demo.domain.Player;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.services.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerServiceUnitTest {

    @Autowired
    private PlayerService playerService;

    @Mock
    private PlayerRepository playerRepository;

    List<Player> players = new ArrayList<>();
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
    public void testGetAllPlayers() {
        Mockito.when(playerRepository.findAll()).thenReturn(players);
        Iterable<Player> listOfPlayers = playerService.getAllPlayers();

        assertThat(listOfPlayers).isNotNull();
        assertThat(listOfPlayers).isEqualTo(players);
    }
}

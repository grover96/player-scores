package com.example.demo.services;

import com.example.demo.domain.Player;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;



@Service
public class PlayerService {

    static Set<Player> players = new HashSet<>();
    {
        Player player1 = new Player(0001, "Michael Jordan", "Chicago Bulls", 80, 29.9f);
        Player player2 = new Player(0002, "Kobe Bryant", "Los Angeles Lakers", 78, 25.4f);
        Player player3 = new Player(0003, "Steph Curry", "Golden State Warriors", 82, 32.8f);

        players.add(player1);
        players.add(player2);
        players.add(player3);

    }

    public Set<Player> getAllPlayers() {
        return players;

    }

    public Player getPlayerById(Long id) {
        for (Player player: players) {
            if(player.getId() == id){
                return player;
            }
        }
        return null;
    }

    public Player addPlayer(Player player) {
        players.add(player);
        return player;
    }
}

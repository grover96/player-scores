package com.example.demo.controller;

import com.example.demo.domain.Player;
import com.example.demo.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/api/players")
    public Set<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/api/players/{id}")
    public Player getPlayerById(@PathVariable("id") Long id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping("/api/players")
    @ResponseStatus(HttpStatus.CREATED)
    public Player addPlayer(@RequestBody Player player) {
        return playerService.addPlayer(player);
    }
}

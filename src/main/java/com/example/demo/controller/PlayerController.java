package com.example.demo.controller;

import com.example.demo.domain.Player;
import com.example.demo.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    public Iterable<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/players/{id}")
    public Player getPlayerById(@PathVariable("id") Long id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping("/players")
    @ResponseStatus(HttpStatus.CREATED)
    public Player addPlayer(@RequestBody Player player) {
        return playerService.save(player);
    }

    @DeleteMapping("/players/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlayer(@PathVariable("id") Long id){
        playerService.delete(id);
    }
}

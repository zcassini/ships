package com.example.ships.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.ships.model.Ship;
import com.example.ships.repository.ShipRepository;

@Configuration
public class LoadDatabase {

    @Bean
    public CommandLineRunner loadDB(ShipRepository shipRepository) {
        return args -> {
            // Make some ships
            Ship ship1 = new Ship("Boaty McBoat Face", "Yamaha", "Speedstar");
            Ship ship2 = new Ship("USS Something", "US Navy", "Destroyer");
            Ship ship3 = new Ship("USS Omaha", "US Navy", "Aircraft Carrier");

            shipRepository.save(ship1);
            shipRepository.save(ship2);
            shipRepository.save(ship3);
            shipRepository.save(new Ship("Tuggy", "XYZ Industries", "Tugboat"));

        };
    }
}

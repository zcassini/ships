package com.example.ships.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ships.model.Ship;
import com.example.ships.repository.ShipRepository;

@RestController
@RequestMapping("/api/ships")
public class ShipController {

    @Autowired
    private ShipRepository shipRepository;

    @GetMapping("")
    public ResponseEntity<List<Ship>> getAllShips() {
        List<Ship> ships = (List<Ship>) shipRepository.findAll();
        if (ships.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ship> getShip(@PathVariable Long id) {
        return shipRepository.findById(id)
                .map(ship -> new ResponseEntity<>(ship, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ship> deleteShip(@PathVariable Long id) {
        return shipRepository.findById(id)
                .map(ship -> {
                    shipRepository.deleteById(id);
                    return new ResponseEntity<>(ship, HttpStatus.OK);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/new")
    public ResponseEntity<Ship> addShip(@RequestBody Ship ship) {
        Ship savedShip = shipRepository.save(ship);
        return new ResponseEntity<>(savedShip, HttpStatus.CREATED);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Ship> updateShip(@PathVariable Long id, @RequestBody Ship updatedShip) {
        return shipRepository.findById(id)
                .map(existingShip -> {
                    if (updatedShip.getName() != null)
                        existingShip.setName(updatedShip.getName());
                    if (updatedShip.getModel() != null)
                        existingShip.setMake(updatedShip.getMake());
                    if (updatedShip.getModel() != null)
                        existingShip.setModel(updatedShip.getModel());
                    Ship savedShip = shipRepository.save(existingShip); 
                    return new ResponseEntity<>(savedShip, HttpStatus.OK);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // @PutMapping("edit/{id}")
    // public Ship updateShip(@PathVariable Long id, @RequestBody Ship updatedShip)
    // {
    // Ship existingShip = shipRepository.findById(id).orElse(null);

    // if (existingShip != null) {
    // if (updatedShip.getName() != null)
    // existingShip.setName(updatedShip.getName());
    // if (updatedShip.getModel() != null)
    // existingShip.setMake(updatedShip.getMake());
    // if (updatedShip.getModel() != null)
    // existingShip.setModel(updatedShip.getModel());
    // return shipRepository.save(existingShip);
    // }

    // return null;
    // }

    // @PostMapping("/new")
    // public Ship addShip(@RequestBody Ship ship) {
    // return shipRepository.save(ship);
    // }

    // @DeleteMapping("/{id}")
    // public Ship deleteShip(@PathVariable Long id) {
    // Ship ship = shipRepository.findById(id).orElse(null);
    // shipRepository.deleteById(id);
    // return ship;
    // }

    // @DeleteMapping("/{id}")
    // public void deleteShip(@PathVariable Long id) {
    // shipRepository.deleteById(id);
    // }

    // @GetMapping("/{id}")
    // public Ship getShip(@PathVariable Long id) {
    // return shipRepository.findById(id).orElse(null);
    // }

    // @GetMapping("")
    // public Iterable<Ship> getAllShips() {
    // return shipRepository.findAll();
    // }

}

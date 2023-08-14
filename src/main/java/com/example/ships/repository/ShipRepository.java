package com.example.ships.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.ships.model.Ship;

public interface ShipRepository extends CrudRepository<Ship, Long> {

}

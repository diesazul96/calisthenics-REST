package com.glb.bootcamp.service;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.glb.bootcamp.model.Routine;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface RoutinesRepository extends CrudRepository<Routine, Integer> {

    List<Routine> findByCreator(String creator);

}
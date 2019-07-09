package com.glb.bootcamp.endpoint;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.glb.bootcamp.model.Routine;
import com.glb.bootcamp.service.RoutinesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoutinesEndpoint {

	@Autowired
	private final RoutinesRepository routinesRepository;

	public RoutinesEndpoint(RoutinesRepository routinesRepository) {
		this.routinesRepository = routinesRepository;
	}

	@GetMapping("/routines")
	public ResponseEntity findAll() {
		Iterable<Routine> all = routinesRepository.findAll();

		if (Objects.nonNull(all)) {
			return ResponseEntity.status(HttpStatus.OK).body(all);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Record Found");
		}
	}

	@GetMapping("/routines/{id}")
	public ResponseEntity findRoutineById(@PathVariable("id") int id) {
		Optional<Routine> routine = routinesRepository.findById(id);

		if (routine.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(routine);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Routine Found with ID: "+id);
		}
	}

	@GetMapping("/routines/creator/{creator}")
	public ResponseEntity findRoutinesByCreator(@PathVariable("creator") String creator) {
		List<Routine> routines = routinesRepository.findByCreator(creator);

		if (routines.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Routine Found for Creator: "+creator);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(routines);
		}
	}
	
	@PostMapping("/routines",produces = "application/json"	)
	public ResponseEntity newRoutine(@RequestBody Routine routine) {
		if (routinesRepository.existsById(routine.getId())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The Routine with ID: "+routine.getId()+
			", already exists!");
		}

		routinesRepository.save(routine);

		if (routinesRepository.existsById(routine.getId())) {
			return ResponseEntity.status(HttpStatus.CREATED).body(routine);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!");
		}
	}

	@PutMapping("/routines")
	public ResponseEntity editRoutine(@RequestBody Routine routine) {
		if (!routinesRepository.existsById(routine.getId())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The Routine with ID: "+routine.getId()+
			", doesn't exists!");
		}

		routinesRepository.save(routine);

		return ResponseEntity.status(HttpStatus.OK).body(routine);
	}

	@DeleteMapping("/routines/{id}")
	public ResponseEntity deleteRoutine(@PathVariable("id") int id) {
		if (routinesRepository.existsById(id)) {
			routinesRepository.deleteById(id);	
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The Routine with ID: "
			+id+", doesn't exists!");
		}

		if (!routinesRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.OK).body("");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!");
		}
	}
}

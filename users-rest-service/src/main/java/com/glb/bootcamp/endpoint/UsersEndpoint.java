package com.glb.bootcamp.endpoint;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import com.glb.bootcamp.model.Routine;
import com.glb.bootcamp.model.User;
import com.glb.bootcamp.service.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UsersEndpoint {

    @Autowired
	private final UsersRepository usersRepository;

	private final RestTemplate restTemplate;

	public UsersEndpoint(UsersRepository usersRepository, RestTemplateBuilder restTemplateBuilder) {
		this.usersRepository = usersRepository;
		this.restTemplate = restTemplateBuilder.build();
	}

    @GetMapping("/users")
	public ResponseEntity findAll() {
		Iterable<User> all = usersRepository.findAll();

		if (all.iterator().hasNext()) {
			return ResponseEntity.status(HttpStatus.OK).body(all);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Record Found");
		}
	}

	@GetMapping("/users/{username}")
	public ResponseEntity findUserByUsername(@PathVariable("username") String username) {
		Optional<User> user = usersRepository.findById(username);

		if (user.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No User Found with username: "+username);
		}
	}

	@GetMapping("/users/{username}/routines")
	public ResponseEntity findAllUserRoutines(@PathVariable("username") String username) {
		List<Routine> routines = 
			this.restTemplate.getForEntity("http://localhost:8000/routines/creator/{creator}", List.class,username).getBody();
		
		if (routines.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Routines Found for username: "+username);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(routines);
		}
	}

	@GetMapping("/users/{username}/routines/{id}")
	public ResponseEntity findUserRoutineById(@PathVariable("username") String username, @PathVariable("id") int id) {
		Optional<User> user = usersRepository.findById(username);
		int[] userRoutines = user.get().getRoutines();

		if (Arrays.stream(userRoutines).filter(x -> x==id).findAny().orElse(-1)!=-1) {
			Routine routine = 
				this.restTemplate.getForEntity("http://localhost:8000/routines/{id}", Routine.class,id).getBody();
			
			return ResponseEntity.status(HttpStatus.OK).body(routine);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Routine With ID: "+id+" Found for username: "+username);
		}
	}
	
	@PostMapping("/users")
	public ResponseEntity newUser(@RequestBody User user) {
		if (usersRepository.existsById(user.getUsername())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The User with username: "+user.getUsername()+
			", already exists!");
		}

		usersRepository.save(user);

		if (usersRepository.existsById(user.getUsername())) {
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!");
		}
	}

	@PutMapping("/users")
	public ResponseEntity editUser(@RequestBody User user) {
		if (!usersRepository.existsById(user.getUsername())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The User with Username: "+user.getUsername()+
			", doesn't exists!");
		}

		usersRepository.save(user);

		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@DeleteMapping("/users/{username}")
	public ResponseEntity deleteUser(@PathVariable("username") String username) {
		if (usersRepository.existsById(username)) {
			usersRepository.deleteById(username);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The User with Username: "+username+
			", doesn't exists!");
		}

		if (!usersRepository.existsById(username)) {
			return ResponseEntity.status(HttpStatus.OK).body("");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!");
		}
	}

}

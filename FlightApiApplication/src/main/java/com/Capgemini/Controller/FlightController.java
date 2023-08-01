package com.Capgemini.Controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Capgemini.Entity.FlightDetails;
import com.Capgemini.Service.FlightService;



@RestController
@RequestMapping("/api/flights")
public class FlightController {
	@Autowired
	private FlightService flightService;

	@GetMapping
	public List<FlightDetails> getAllFlights() {
		return flightService.getAllFlights();
	}

    @GetMapping("/flights")
	public List<FlightDetails> getFlights(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Long id
    ) {
        if (id != null) {
            // Get a single flight by ID
            FlightDetails flight = flightService.getFlightById(id);
            return List.of(flight);
        } else if ("duration".equalsIgnoreCase(sortBy)) {
            // Get flights sorted by duration
            return flightService.getFlightsSortedByDuration();
        } else {
            // Get flights sorted by price (default if sortBy is not specified)
            return flightService.getFlightsSortedByPrice();
        }
    }

	@GetMapping("find-list")
	public List<FlightDetails> getFlightsByOriginAndDestination(@RequestParam String origin,
			@RequestParam String destination) {
		return flightService.getFlightsByOriginAndDestination(origin, destination);
	}

	@PostMapping
	public ResponseEntity<FlightDetails> createFlight(@Valid @RequestBody FlightDetails flight) {
		FlightDetails savedFlight = flightService.createFlight(flight);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedFlight);
	}

	@PutMapping("/{id}")
	public ResponseEntity<FlightDetails> updateFlight(@PathVariable Long id, @RequestBody FlightDetails flight) {
		FlightDetails updatedFlight = flightService.updateFlight(id, flight);
		if (updatedFlight != null) {
			return ResponseEntity.ok(updatedFlight);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
		flightService.deleteFlight(id);
		return ResponseEntity.noContent().build();
	}
}
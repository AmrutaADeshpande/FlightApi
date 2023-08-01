package com.Capgemini.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String flightNumber;
	@NotNull
	private String origin;
	@NotNull
	private String destination;
	@NotNull
	private LocalDateTime departureTime;
	@Null
	private LocalDateTime arrivalTime;

	private double price;

	public long getDurationInMinutes() {
		return java.time.Duration.between(departureTime, arrivalTime).toMinutes();
	}
}

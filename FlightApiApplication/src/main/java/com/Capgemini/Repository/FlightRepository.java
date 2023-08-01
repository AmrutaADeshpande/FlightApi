package com.Capgemini.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Capgemini.Entity.FlightDetails;


@Repository
public interface FlightRepository extends JpaRepository<FlightDetails, Long> {

}

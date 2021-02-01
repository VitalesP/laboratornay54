package com.lab54.laboratornay54.repository;

import com.lab54.laboratornay54.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    boolean existsById(Long id);
    Optional<Event> findById(Long id);
}

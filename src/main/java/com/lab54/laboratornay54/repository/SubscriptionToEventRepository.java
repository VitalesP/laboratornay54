package com.lab54.laboratornay54.repository;

import com.lab54.laboratornay54.model.SubscriptionToEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionToEventRepository extends JpaRepository<SubscriptionToEvent, Long> {
    boolean existsByEmail(String email);
    Optional<SubscriptionToEvent> findById(Long id);

    List<SubscriptionToEvent> findByEmail(String email);
}

package com.lab54.laboratornay54.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "subscription_to_events")
public class SubscriptionToEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Column(name = "event")
    private List<Event> event;

    private String email;

    @Column(name = "date")
    private LocalDate localDate;
}

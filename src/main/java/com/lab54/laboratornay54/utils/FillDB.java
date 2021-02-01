package com.lab54.laboratornay54.utils;

import com.lab54.laboratornay54.model.Event;
import com.lab54.laboratornay54.model.SubscriptionToEvent;
import com.lab54.laboratornay54.repository.EventRepository;
import com.lab54.laboratornay54.repository.SubscriptionToEventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class FillDB {
    private Random rnd = new Random();

    @Bean
    CommandLineRunner initDatabase(EventRepository eventRepository
            , SubscriptionToEventRepository subscriptionToEventRepository) {
        return (args) -> {
            eventRepository.deleteAll();
            subscriptionToEventRepository.deleteAll();
            eventRepository.saveAll(getEvents());
            List<SubscriptionToEvent> subscriptionToEvents = getSubscriptionToEvent(eventRepository);
            subscriptionToEventRepository.saveAll(subscriptionToEvents);

        };
    }
    private List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        for (int i = 1; i <= 5; i++){
            events.add(Event.builder()
                    .name("event" + i)
                    .description("description" + i)
                    .localDate(LocalDate.now())
                    .build()
            );
        }
        return events;
    }
    private List<SubscriptionToEvent>
    getSubscriptionToEvent(EventRepository eventRepository) {
        List<SubscriptionToEvent> subscriptions = new ArrayList<>();
        for (int i = 1; i <= 5; i++){
            subscriptions.add(SubscriptionToEvent.builder()
                    .email("EMAIL" + i + "@email.com")
                    .event(eventRepository.findAll().get(i - 1))
                    .localDate(LocalDate.now())
                    .build()
            );
        }
        return subscriptions;


    }
}

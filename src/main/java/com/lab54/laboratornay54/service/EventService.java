package com.lab54.laboratornay54.service;

import com.lab54.laboratornay54.model.Event;
import com.lab54.laboratornay54.model.SubscriptionToEvent;
import com.lab54.laboratornay54.repository.SubscriptionToEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import com.lab54.laboratornay54.repository.EventRepository;

import java.time.LocalDate;


@Service
public class EventService {

    private final EventRepository eventRepository;
    private final SubscriptionToEventRepository subscriptionToEventRepository;

    public EventService(EventRepository eventRepository, SubscriptionToEventRepository subscriptionToEventRepository) {
        this.eventRepository = eventRepository;
        this.subscriptionToEventRepository = subscriptionToEventRepository;
    }

    public Slice<Event> getEvent(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    public boolean subscribeToEvent(String event_id, String email) {
        if(eventRepository.existsById(Long.valueOf(event_id))
                && subscriptionToEventRepository.existsByEmail(email)
                && subscriptionToEventRepository.findById(Long.valueOf(event_id)).get().getLocalDate()
                    .isBefore(LocalDate.now())){
            subscriptionToEventRepository.save(SubscriptionToEvent.builder()
                    .email(email)
                    .event(eventRepository.findById(Long.valueOf(event_id)).get())
                    .localDate(LocalDate.now())
                    .build());
                return true;
        } else {
            return false;
        }
    }
}

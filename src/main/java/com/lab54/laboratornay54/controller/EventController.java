package com.lab54.laboratornay54.controller;

import com.lab54.laboratornay54.model.Event;
import com.lab54.laboratornay54.modelDTO.AnswerDTO;
import com.lab54.laboratornay54.service.EventService;
import com.lab54.laboratornay54.service.SubscriptionToEventService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;
    private final SubscriptionToEventService subscriptionToEventService;

    public EventController(EventService eventService, SubscriptionToEventService subscriptionToEventService) {
        this.eventService = eventService;
        this.subscriptionToEventService = subscriptionToEventService;
    }

    @GetMapping
    public Slice<Event> findMovies(Pageable pageable) {
        return eventService.getEvent(pageable);
    }

    @PostMapping("/{event_id}/{email}")
    public ResponseEntity<AnswerDTO> subscribeToEvent(@PathVariable String event_id, @PathVariable String email) {
        if (eventService.subscribeToEvent(event_id, email)) {
            return ResponseEntity.ok().body(AnswerDTO.builder()
                    .subscriptionToEventId(subscriptionToEventService.findById(event_id).getId().toString())
                    .textAnswer("Подписка успешно офомлена")
                    .build());
        } else
        return ResponseEntity.badRequest().body(AnswerDTO.builder()
                .textAnswer("Подписка уже была офомлена")
                .build());
    }



}

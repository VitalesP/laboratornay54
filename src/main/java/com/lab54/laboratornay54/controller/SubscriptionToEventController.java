package com.lab54.laboratornay54.controller;

import com.lab54.laboratornay54.model.SubscriptionToEvent;
import com.lab54.laboratornay54.modelDTO.AnswerDTO;
import com.lab54.laboratornay54.modelDTO.SubscriptionToEventDto;
import com.lab54.laboratornay54.service.SubscriptionToEventService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subscription")
public class SubscriptionToEventController {
    private final SubscriptionToEventService subscriptionToEventService;

    public SubscriptionToEventController(SubscriptionToEventService subscriptionToEventService) {
        this.subscriptionToEventService = subscriptionToEventService;
    }

    @GetMapping
    public Slice<SubscriptionToEvent> findSubscription( Pageable pageable) {
        return subscriptionToEventService.getSubscription(pageable);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteReview(@RequestParam String id, @RequestParam String email) {
        if (subscriptionToEventService.deleteSubscriptionToEvent(id))
            return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/byEmail/{email}")
    public ResponseEntity<?> getSubscribeToEventForEmail(@PathVariable String email){
        if(subscriptionToEventService.existEmail(email)){
            var subscribtions=subscriptionToEventService.findByEmail(email);
            var subscribtionsDto=subscribtions.stream().map(SubscriptionToEventDto::from).collect(Collectors.toList());
            return ResponseEntity.ok(subscribtionsDto);
        } else
            return ResponseEntity.badRequest().body(AnswerDTO.builder()
                    .textAnswer("Такой почты нет.")
                    .build());
    }


}

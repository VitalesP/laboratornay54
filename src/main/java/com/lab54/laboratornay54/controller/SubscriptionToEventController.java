package com.lab54.laboratornay54.controller;

import com.lab54.laboratornay54.model.SubscriptionToEvent;
import com.lab54.laboratornay54.modelDTO.AnswerDTO;
import com.lab54.laboratornay54.service.SubscriptionToEventService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/byEmail")
    public ResponseEntity<AnswerDTO> getSubscribeToEventForEmail(@RequestParam String email){
        if(subscriptionToEventService.existEmail(email)){
            return ResponseEntity.ok().body(AnswerDTO.builder()
                    .subscriptionToEventId(subscriptionToEventService.findByEmail(email).toString())
                    .build());
        } else
            return ResponseEntity.badRequest().body(AnswerDTO.builder()
                    .textAnswer("Такой почты нет.")
                    .build());
    }


}

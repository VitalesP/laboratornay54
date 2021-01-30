package com.lab54.laboratornay54.modelDTO;

import com.lab54.laboratornay54.model.Event;
import com.lab54.laboratornay54.model.SubscriptionToEvent;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class SubscriptionToEventDto {// extends SubscriptionToEvent {

    public static SubscriptionToEventDto from(SubscriptionToEvent subscriptionToEvent){
        return builder()
                .id(subscriptionToEvent.getId())
                .event(subscriptionToEvent.getEvent())
                .email(subscriptionToEvent.getEmail())
                .localDate(subscriptionToEvent.getLocalDate())
                .build();
    }

    private Long id;
    private List<Event> event;
    private String email;
    private LocalDate localDate;

}

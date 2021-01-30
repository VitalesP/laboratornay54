package com.lab54.laboratornay54.modelDTO;

import com.lab54.laboratornay54.model.Event;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class EventDto {

    public static EventDto from(Event event) {
        return builder()
                .id(event.getId())
                .name(event.getName())
                .localDate(event.getLocalDate())
                .description(event.getDescription())
                .build();
    }

    private Long id;
    private String name;
    private LocalDate localDate;
    private String description;

}

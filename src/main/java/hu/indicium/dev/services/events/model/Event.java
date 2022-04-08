package hu.indicium.dev.services.events.model;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedEntity
public class Event {
    @Id
    private String id;

    private String title;

    private String description;

    private String slug;

    private LocalDateTime startDateTime;

    private LocalDateTime endDatetime;
}

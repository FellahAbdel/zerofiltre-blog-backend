package tech.zerofiltre.blog.domain.newsletter.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Newsletter {
    private long id;
    private LocalDateTime subscribedAt = LocalDateTime.now();
    private LocalDateTime unsubscribedAt = LocalDateTime.now();
    private Boolean isSubscribed = false;
    private long userId;

}

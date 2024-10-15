package tech.zerofiltre.blog.infra.providers.database.newsletter.model;

import lombok.Data;
import tech.zerofiltre.blog.infra.providers.database.BaseEntityJPA;

import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Table(name="newsletterSubscription")
public class NewsletterJPA extends BaseEntityJPA {
    private LocalDateTime subscribedAt = LocalDateTime.now();
    private LocalDateTime unsubscribedAt = LocalDateTime.now();
    private Boolean isSubscribed = false;
    private long userId;
}

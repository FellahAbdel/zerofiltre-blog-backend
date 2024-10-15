package tech.zerofiltre.blog.domain.newsletter;

import org.springframework.stereotype.Component;
import tech.zerofiltre.blog.domain.newsletter.domain.Newsletter;

import java.util.List;

public interface NewsletterProvider {
    Newsletter save(Newsletter newsletter);
    void subscribe(long userId);
    void unsubscribe(long userId);
    List<Long> getAllSubscribers();
}

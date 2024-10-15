package tech.zerofiltre.blog.domain.newsletter.features.newsletter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tech.zerofiltre.blog.domain.newsletter.NewsletterProvider;
import tech.zerofiltre.blog.domain.newsletter.domain.Newsletter;
import tech.zerofiltre.blog.domain.user.UserProvider;

import java.time.LocalDateTime;

public class NewsletterService {
    private final UserProvider userProvider;
    private final NewsletterProvider newsletterProvider;

    public NewsletterService(UserProvider userProvider, NewsletterProvider newsletterProvider) {
        this.userProvider = userProvider;
        this.newsletterProvider = newsletterProvider;
    }

    public Newsletter init(long userId){
        Newsletter newsletter = new Newsletter();
        newsletter.setSubscribedAt(LocalDateTime.now());
        newsletter.setUnsubscribedAt(null);
        newsletter.setIsSubscribed(true);
        newsletter.setUserId(userId);
        return newsletterProvider.save(newsletter);
    }

    public void unsubscribeUser(long userId) {

    }
}

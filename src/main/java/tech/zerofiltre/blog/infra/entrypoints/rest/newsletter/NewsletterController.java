package tech.zerofiltre.blog.infra.entrypoints.rest.newsletter;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.zerofiltre.blog.domain.newsletter.NewsletterProvider;
import tech.zerofiltre.blog.domain.newsletter.domain.Newsletter;
import tech.zerofiltre.blog.domain.newsletter.features.newsletter.NewsletterService;
import tech.zerofiltre.blog.domain.user.UserProvider;
import tech.zerofiltre.blog.infra.entrypoints.rest.SecurityContextManager;

@RestController
@RequestMapping("/newsletter")
public class NewsletterController {
    private final NewsletterService newsletterService;
    private final SecurityContextManager securityContext;

    public NewsletterController(SecurityContextManager securityContext, UserProvider userProvider,
                                NewsletterProvider newsletterProvider) {
        this.securityContext = securityContext;
        this.newsletterService = new NewsletterService(userProvider, newsletterProvider);
    }

    @DeleteMapping
    public Newsletter unSubscribe(@RequestParam long userId) {
        // TODO : Unsubscribe the user from the newsletter by using the newsletterService.
        return null;
        // return newsletterService.unsubscribeUser(userId);
    }

    // TODO : Should listen to an event for new course or article publication and then inform all the subscribe users
    // TODO : Use the email template for broadcasting the news.

}

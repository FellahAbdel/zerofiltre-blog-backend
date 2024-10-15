package tech.zerofiltre.blog.infra.providers.database.newsletter;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.zerofiltre.blog.infra.providers.database.newsletter.model.NewsletterJPA;

public interface NewsletterJPARepository extends JpaRepository<NewsletterJPA, Long> {
}

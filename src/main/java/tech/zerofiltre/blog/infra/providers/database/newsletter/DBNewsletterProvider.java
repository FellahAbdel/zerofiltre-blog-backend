package tech.zerofiltre.blog.infra.providers.database.newsletter;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tech.zerofiltre.blog.domain.newsletter.NewsletterProvider;
import tech.zerofiltre.blog.domain.newsletter.domain.Newsletter;
import tech.zerofiltre.blog.infra.providers.database.newsletter.mapper.NewsletterJPAMapper;
import tech.zerofiltre.blog.infra.providers.database.user.mapper.UserJPAMapper;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class DBNewsletterProvider implements NewsletterProvider {
    private final NewsletterJPAMapper mapper = Mappers.getMapper(NewsletterJPAMapper.class);
    private final NewsletterJPARepository repository;

    @Override
    public Newsletter save(Newsletter newsletter) {
        // TODO : We should save this by using the mapper.
        return mapper.fromJPA(repository.save(mapper.toJPA(newsletter)));
    }

    @Override
    public void subscribe(long userId) {

    }

    @Override
    public void unsubscribe(long userId) {

    }

    @Override
    public List<Long> getAllSubscribers() {
        return List.of();
    }
}

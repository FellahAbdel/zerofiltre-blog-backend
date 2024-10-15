package tech.zerofiltre.blog.infra.providers.database.newsletter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.zerofiltre.blog.domain.newsletter.domain.Newsletter;
import tech.zerofiltre.blog.infra.providers.database.newsletter.model.NewsletterJPA;

@Mapper()
public interface NewsletterJPAMapper {

    NewsletterJPA toJPA(Newsletter newsletter);

    Newsletter fromJPA(NewsletterJPA newsletter);
}

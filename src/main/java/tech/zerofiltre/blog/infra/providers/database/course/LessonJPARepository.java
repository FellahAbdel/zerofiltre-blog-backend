package tech.zerofiltre.blog.infra.providers.database.course;

import org.springframework.data.jpa.repository.*;
import tech.zerofiltre.blog.infra.providers.database.course.model.*;

public interface LessonJPARepository extends JpaRepository<LessonJPA, Long> {

}


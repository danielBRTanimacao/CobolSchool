package CobolSchool.repository;

import CobolSchool.entities.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<LessonEntity, Long> {
}

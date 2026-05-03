package CobolSchool.service;

import CobolSchool.DTOs.lessons.RequestLessonDTO;
import CobolSchool.entities.CourseEntity;
import CobolSchool.entities.LessonEntity;
import CobolSchool.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository repository;

    public void saveLesson(RequestLessonDTO data) {
        LessonEntity lesson = new LessonEntity();
        lesson.setName(data.name());

        String fileName = strProcess.storeFile(data.video());

        repository.save(lesson);
    }

    void updt() {}
    void del() {}
}

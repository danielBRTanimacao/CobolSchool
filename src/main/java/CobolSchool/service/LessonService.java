package CobolSchool.service;

import CobolSchool.DTOs.lessons.RequestLessonDTO;
import CobolSchool.entities.CourseEntity;
import CobolSchool.entities.LessonEntity;
import CobolSchool.repository.LessonRepository;
import CobolSchool.utils.StorageProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository repository;

    private final StorageProcess strProcess;

    public void saveLesson(RequestLessonDTO data) {
        LessonEntity lesson = new LessonEntity();
        lesson.setName(data.name());

        String fileName = strProcess.storeFile(data.image()); // pre saving image and init save video data
        String videoName = strProcess.storeVideo(data.video());

        repository.save(lesson);
    }

    void updt() {}
    void del() {}
}

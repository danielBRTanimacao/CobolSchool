package CobolSchool.service;

import CobolSchool.DTOs.lessons.RequestLessonDTO;
import CobolSchool.entities.LessonEntity;
import CobolSchool.repository.LessonRepository;
import CobolSchool.utils.StorageProcess;
import CobolSchool.utils.customs.NotFoundException;
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

        String fileName = strProcess.storeFile(data.image());
        lesson.setThumbnailPath(fileName);
        String videoName = strProcess.storeVideo(data.video());
        lesson.setVideoPath(videoName);

        repository.save(lesson);
    }

    void updt() {}

    public void deleteLesson(Long id) {
        LessonEntity lesson = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Lesson not found")
        );
        repository.delete(lesson);
    }
}

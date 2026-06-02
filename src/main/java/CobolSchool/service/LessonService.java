package CobolSchool.service;

import CobolSchool.DTOs.lessons.RequestLessonDTO;
import CobolSchool.DTOs.lessons.RequestUpdateLessonDTO;
import CobolSchool.entities.LessonEntity;
import CobolSchool.repository.LessonRepository;
import CobolSchool.utils.StorageProcess;
import CobolSchool.utils.customs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LessonService {

    private final LessonRepository repository;
    private final StorageProcess strProcess;

    public void saveLesson(RequestLessonDTO data) {
        log.info("Starting lesson creation for name: {}", data.name());

        LessonEntity lesson = new LessonEntity();
        lesson.setName(data.name());

        String fileName = strProcess.storeFile(data.image());
        lesson.setThumbnailPath(fileName);

        String videoName = strProcess.storeVideo(data.video());
        lesson.setVideoPath(videoName);

        LessonEntity savedLesson = repository.save(lesson);
        log.info("Lesson successfully created with ID: {}", savedLesson.getId());
    }

    public void updateLesson(RequestUpdateLessonDTO data, Long id) {
        log.info("Starting update for lesson with ID: {}", id);

        LessonEntity lesson = repository.findById(id).orElseThrow(() -> {
            log.warn("Update failed: Lesson with ID {} not found", id);
            return new NotFoundException("Lesson not found");
        });

        if (data.name() != null) {
            log.debug("Updating name for lesson ID {} to: {}", id, data.name());
            lesson.setName(data.name());
        }
        if (data.image() != null) {
            log.debug("Updating thumbnail for lesson ID {}", id);
            String fileName = strProcess.storeFile(data.image());
            lesson.setThumbnailPath(fileName);
        }
        if (data.video() != null) {
            log.debug("Updating video file for lesson ID {}", id);
            String videoName = strProcess.storeVideo(data.video());
            lesson.setVideoPath(videoName);
        }

        repository.save(lesson);
        log.info("Lesson with ID {} successfully updated", id);
    }

    public void deleteLesson(Long id) {
        log.info("Attempting to delete lesson with ID: {}", id);

        LessonEntity lesson = repository.findById(id).orElseThrow(() -> {
            log.warn("Delete failed: Lesson with ID {} not found", id);
            return new NotFoundException("Lesson not found");
        });

        repository.delete(lesson);
        log.info("Lesson with ID {} successfully deleted", id);
    }
}
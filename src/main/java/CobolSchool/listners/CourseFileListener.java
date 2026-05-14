package CobolSchool.listners;

import CobolSchool.entities.CourseEntity;
import jakarta.persistence.PostRemove;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class CourseFileListener {
    @PostRemove
    public void deleteThumbnailFile(CourseEntity course) {
        if (course.getThumbnailPath() != null) {
            try {
                Path path = Paths.get(course.getThumbnailPath());
                Files.deleteIfExists(path);
            } catch (Exception e) {
                log.error("Could not delete file: {}", e.getMessage());
            }
        }
    }
}

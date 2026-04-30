package CobolSchool.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class StorageProcess {

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${upload.video.path}")
    private String uploadVideoPath;

    public String storeFile(MultipartFile file) {
        return store(file, uploadPath);
    }

    public String storeVideo(MultipartFile file) {
        return store(file, uploadVideoPath);
    }

    private String store(MultipartFile file, String destinationPath) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file.");
            }

            Path rootPath = Paths.get(destinationPath).toAbsolutePath().normalize();

            if (!Files.exists(rootPath)) {
                Files.createDirectories(rootPath);
            }

            String originalFileName = file.getOriginalFilename() != null ? file.getOriginalFilename() : "file";
            String cleanFileName = originalFileName.replaceAll("[^a-zA-Z0-9.\\-]", "_");
            String fileName = UUID.randomUUID() + "_" + cleanFileName;

            Path targetLocation = rootPath.resolve(fileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Err saving file: " + ex.getMessage());
        }
    }
}
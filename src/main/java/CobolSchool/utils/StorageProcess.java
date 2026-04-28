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

    public String storeFile(MultipartFile file) {
        try {
            Path rootPath = Paths.get(uploadPath).toAbsolutePath().normalize();

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

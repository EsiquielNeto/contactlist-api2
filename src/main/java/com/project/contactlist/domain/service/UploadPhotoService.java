package com.project.contactlist.domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadPhotoService {

    @Value("${contact.disc.root}")
    private String root;

    @Value("${contact.disc.directory-photos}")
    private String photoDir;

    public String uploadPhoto(MultipartFile file) {

        Path directoryPath = Paths.get(this.root, photoDir);
        Path path = directoryPath.resolve(file.getOriginalFilename());

        try {
            Files.createDirectories(directoryPath);
            file.transferTo(path.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
        }

        return path.toAbsolutePath().toString();
    }

    public void removePhoto(MultipartFile multipartFile, String pathPhoto ) {
        File directory = new File("pathPhoto");
        File[] listFiles = directory.listFiles();

        for(File f : listFiles) {
            if (f.getName().equalsIgnoreCase(multipartFile.getOriginalFilename())){
                f.delete();
                break;
            }
        }
    }
}


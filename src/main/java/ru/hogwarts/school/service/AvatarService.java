package ru.hogwarts.school.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional

public class AvatarService {

    @Value(value = "avatars")
    private String avatarDir;

    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }


    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        Student student = studentService.findStudentById(studentId);

        Path filepath = Path.of(avatarDir, studentId + "." + getExtension(Objects.requireNonNull(file.getOriginalFilename())));
        Files.createDirectories((filepath.getParent()));
        Files.deleteIfExists(filepath);
        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filepath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }

        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filepath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(generateImagePreview(filepath));

        avatarRepository.save(avatar);

    }

        public Avatar findAvatar(Long id) {
            return avatarRepository.findByStudentId(id).orElse(new Avatar());
        }

        private byte[] generateImagePreview(Path filePath) throws IOException{
            try (InputStream is = Files.newInputStream(filePath);
                 BufferedInputStream bis = new BufferedInputStream(is, 1024);
                 ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                BufferedImage image = ImageIO.read(bis);
                int height = image.getHeight() / (image.getWidth() / 100);
                BufferedImage preview = new BufferedImage(100, height, image.getType());
                Graphics2D graphics = preview.createGraphics();
                graphics.drawImage(image, 0, 0, 100, height, null);
                graphics.dispose();
                ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baos);
                return baos.toByteArray();
            }
        }

        private String getExtension(String filename) {
            return filename.substring(filename.lastIndexOf(".") + 1);
        }

}

package knu.project.filecompressorweb.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import knu.project.filecompressorweb.api.model.FileModel;
import knu.project.filecompressorweb.domain.entity.File;
import knu.project.filecompressorweb.domain.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileCrudService {

    private final FileRepository fileRepository;
    private final UserService userService;

    private final ObjectMapper objectMapper;

    public List<File> getFilesByUserId(Long userId) {
        return fileRepository.findByUserId(userId);
    }

    public void deleteFile(Long fileId) {
        fileRepository.deleteById(fileId);
    }

    public File updateFileName(Long fileId, String newFileName) {
        File file = fileRepository.findById(fileId).orElseThrow(() -> new RuntimeException("File not found"));
        file.setFileName(newFileName);
        return fileRepository.save(file);
    }

    public FileModel saveFile(MultipartFile multipartFile, Long userId) throws IOException {
        File file = new File();
        file.setFileName(multipartFile.getOriginalFilename());
        file.setContent(multipartFile.getBytes());
        file.setCreationDate(new Date());
        file.setUser(userService.getUserById(userId).orElseThrow());

        file = fileRepository.save(file);
        return objectMapper.convertValue(file, FileModel.class); // Assuming you have ObjectMapper to convert entities to models
    }
}


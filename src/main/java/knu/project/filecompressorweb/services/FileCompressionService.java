package knu.project.filecompressorweb.services;

import knu.project.filecompressorweb.domain.entity.File;
import knu.project.filecompressorweb.domain.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileCompressionService {

    private FileRepository fileRepository;

    public File compressFile(Long fileId, String algorithm) {
        File file = fileRepository.findById(fileId).orElseThrow(() -> new RuntimeException("File not found"));

        // Implement your compression logic here
        // If the algorithm is null, select the best matching algorithm

        return file; // Return the compressed file
    }

    // Additional methods related to file compression
}


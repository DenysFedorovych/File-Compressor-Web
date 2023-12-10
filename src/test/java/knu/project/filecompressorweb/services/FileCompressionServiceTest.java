package knu.project.filecompressorweb.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import knu.project.filecompressorweb.domain.entity.File;
import knu.project.filecompressorweb.domain.repository.FileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class FileCompressionServiceTest {

    @Mock
    private FileRepository fileRepository;

    private FileCompressionService fileCompressionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fileCompressionService = new FileCompressionService(fileRepository);
        fileRepository.save(createExampleFile());
    }

    private File createExampleFile() {
        File mockFile = new File();
        mockFile.setFileName("Test");
        mockFile.setId(1L);
        mockFile.setContent(new byte[100]);
        return mockFile;
    }

    @Test
    void testCompressFileWithSupportedAlgorithm() {
        Long fileId = 1L;
        String algorithm = "GZIP";
        File mockFile = createExampleFile();

        when(fileRepository.findById(fileId)).thenReturn(Optional.of(mockFile));

        File result = fileCompressionService.compressFile(fileId, algorithm);

        assertNotNull(result.getContent());
        assertTrue(result.getFileName().endsWith(".gz"));
    }



    @Test
    void testCompressFileWithUnsupportedAlgorithm() {
        Long fileId = 1L;
        String algorithm = "UnsupportedAlgorithm";

        assertThrows(RuntimeException.class, () -> {
            fileCompressionService.compressFile(fileId, algorithm);
        });
    }

    @Test
    void testCompressNonExistentFile() {
        Long fileId = 2L;
        String algorithm = "GZIP";
        when(fileRepository.findById(fileId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            fileCompressionService.compressFile(fileId, algorithm);
        });
    }
}

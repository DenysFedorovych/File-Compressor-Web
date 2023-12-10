package knu.project.filecompressorweb.services;

import knu.project.filecompressorweb.domain.entity.File;
import knu.project.filecompressorweb.domain.repository.FileRepository;
import knu.project.filecompressorweb.services.compression.Compressor;
import knu.project.filecompressorweb.services.compression.GZIPCompressor;
import knu.project.filecompressorweb.services.compression.NoCompressionCompressor;
import knu.project.filecompressorweb.services.compression.XZCompressor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class FileCompressionService {

    private final FileRepository fileRepository;

    private static final Map<String, Compressor> algorithmToCompressor = new ConcurrentHashMap<>();
    static {
        algorithmToCompressor.put("No compression", new NoCompressionCompressor());
        algorithmToCompressor.put("GZIP", new GZIPCompressor());
        algorithmToCompressor.put("XZ", new XZCompressor());
    }

    public File compressFile(Long fileId, String algorithm) {
        File file = fileRepository.findById(fileId).orElseThrow(() -> new RuntimeException("File not found"));

        Compressor compressor = algorithmToCompressor.get(algorithm);

        if (compressor == null) {
            throw new RuntimeException("Unsupported compression format: " + algorithm);
        }

        file.setContent(compressor.compressByteArray(file.getContent()));
        file.setFileName(file.getFileName() + compressor.getCompressedFileExtention());

        return file; // Return the compressed file
    }
}


package knu.project.filecompressorweb.services.compression;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GZIPCompressor implements Compressor {
    @Override
    public byte[] compressByteArray(byte[] input) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
            gzipOutputStream.write(input);
            gzipOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Failed to compress data", e);
        }
    }

    @Override
    public String getCompressedFileExtention() {
        return ".gz";
    }
}


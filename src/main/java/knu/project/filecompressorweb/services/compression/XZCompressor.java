package knu.project.filecompressorweb.services.compression;

import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.XZOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class XZCompressor implements Compressor {
    @Override
    public byte[] compressByteArray(byte[] input) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             XZOutputStream xzOutputStream = new XZOutputStream(byteArrayOutputStream, new LZMA2Options())) {
            xzOutputStream.write(input);
            xzOutputStream.finish();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Failed to compress data", e);
        }
    }

    @Override
    public String getCompressedFileExtention() {
        return ".xz";
    }
}


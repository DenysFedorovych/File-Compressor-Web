package knu.project.filecompressorweb.services.compression;

public class NoCompressionCompressor implements Compressor {
    @Override
    public byte[] compressByteArray(byte[] input) {
        return input;
    }

    @Override
    public String getCompressedFileExtention() {
        return "";
    }
}


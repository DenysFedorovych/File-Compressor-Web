package knu.project.filecompressorweb.services.compression;

public interface Compressor {
    /**
     * Compresses byte array with specified algorithm
     * @param input - uncomressed byte array
     * @return compressed byte array
     */
    byte[] compressByteArray(byte[] input);

    /**
     * Returns extension for the compressed file related to algorithm compression (for example, ".gz" for GZIP).
     * @return extension for the compressed file
     */
    String getCompressedFileExtention();
}

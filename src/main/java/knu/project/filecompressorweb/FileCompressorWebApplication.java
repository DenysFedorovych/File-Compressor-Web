package knu.project.filecompressorweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"knu.project.filecompressorweb.domain.entity"})
public class FileCompressorWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileCompressorWebApplication.class, args);
    }

}

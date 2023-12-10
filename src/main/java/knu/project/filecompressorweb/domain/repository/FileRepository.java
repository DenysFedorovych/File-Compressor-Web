package knu.project.filecompressorweb.domain.repository;

import knu.project.filecompressorweb.domain.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    // Custom methods can also be added here, for example:
     List<File> findByUserId(Long userId);
}

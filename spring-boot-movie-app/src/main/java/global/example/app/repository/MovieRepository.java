package global.example.app.repository;

import global.example.app.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    @Query(value = "SELECT * FROM movies WHERE title LIKE CONCAT('%', :title, '%')", nativeQuery = true)
    List<MovieEntity> findByTitle(String title);
}
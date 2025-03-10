package global.example.app.controller;

import global.example.app.dto.MovieDTO;
import global.example.app.entity.MovieEntity;
import global.example.app.repository.MovieRepository;
import global.example.app.service.OmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private OmdbService omdbService;

    @Autowired
    private MovieRepository movieRepository;

    public AdminController (OmdbService omdbService, MovieRepository movieRepository) {
        this.omdbService = omdbService;
        this.movieRepository = movieRepository;
    }

    @GetMapping("/list")
    public ResponseEntity<List<MovieEntity>> listAllMovies() {
        return ResponseEntity.ok(movieRepository.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDTO>> searchMovies(@RequestParam("query") String query) {
        List<MovieDTO> results = omdbService.searchMovies(query);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/add")
    public ResponseEntity<MovieEntity> addMovie(@RequestBody MovieDTO movieDTO) {
        MovieEntity movie = MovieEntity.builder()
                .title(movieDTO.getTitle())
                .year(movieDTO.getYear())
                .imdbID(movieDTO.getImdbID())
                .posterUrl(movieDTO.getPoster())
                .build();
        MovieEntity saved = movieRepository.save(movie);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        movieRepository.deleteById(id);
        return ResponseEntity.ok("Movie with ID " + id + " has been deleted.");
    }
}
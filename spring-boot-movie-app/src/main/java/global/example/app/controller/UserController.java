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
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private OmdbService omdbService;

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/search")
    public ResponseEntity<List<MovieEntity>> searchMovies(@RequestParam("query") String query) {
        List<MovieEntity> results = movieRepository.findByTitle(query);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieEntity>> getAllMovies() {
        return ResponseEntity.ok(movieRepository.findAll());
    }
}
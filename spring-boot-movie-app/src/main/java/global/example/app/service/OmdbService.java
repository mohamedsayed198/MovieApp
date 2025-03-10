package global.example.app.service;

import global.example.app.dto.MovieDTO;
import global.example.app.dto.OmdbSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OmdbService {

    @Value("omdb.api.key")
    private String API_KEY;

    private final RestTemplate restTemplate;

    @Autowired
    public OmdbService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<MovieDTO> searchMovies(String query) {
        String url = "http://www.omdbapi.com/?apikey=" + API_KEY + "&s=" + query;
        OmdbSearchResponse response = restTemplate.getForObject(url, OmdbSearchResponse.class);

        if (response != null && response.getSearch() != null) {
            return response.getSearch();
        }
        return new ArrayList<>();
    }

    public MovieDTO getMovieByImdbId(String imdbID) {
        String url = "http://www.omdbapi.com/?apikey=" + API_KEY + "&i=" + imdbID;
        return restTemplate.getForObject(url, MovieDTO.class);
    }
}

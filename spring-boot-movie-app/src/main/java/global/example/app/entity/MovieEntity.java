package global.example.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "movies")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    @Column(name = "movie_name")
    private String title;

    @Column(name = "movie_year")
    private String year;

    @Column(name = "movie_imdbId")
    private String imdbID;

    @Column(name = "movie_posterUrl")
    private String posterUrl;
}
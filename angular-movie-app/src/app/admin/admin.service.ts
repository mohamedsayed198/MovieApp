import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface MovieDTO {
  Title: string;
  Year: string;
  imdbID: string;
  Poster?: string;
}

export interface MovieEntity {
  id: number;
  title: string;
  year: string;
  imdbID: string;
  posterUrl?: string;
}

@Injectable({
  providedIn: 'root'
})

export class AdminService {
  private BASE_URL = 'http://localhost:8080/api/admin';

  constructor(private http: HttpClient) {}

  searchMovies(query: string): Observable<MovieDTO[]> {
    return this.http.get<MovieDTO[]>(`${this.BASE_URL}/search?query=${query}`);
  }

  addMovie(movie: MovieDTO): Observable<MovieEntity> {
    return this.http.post<MovieEntity>(`${this.BASE_URL}/add`, movie);
  }

  deleteMovie(id: number): Observable<any> {
    return this.http.delete(`${this.BASE_URL}/delete/${id}`, { responseType: 'text' });
  }

  listAllMovies(): Observable<MovieEntity[]> {
    return this.http.get<MovieEntity[]>(`${this.BASE_URL}/list`);
  }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

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
export class UserService {
  private BASE_URL = 'http://localhost:8080/api/user';

  constructor(private http: HttpClient) {}

  searchMovies(query: string): Observable<MovieEntity[]> {
    return this.http.get<MovieEntity[]>(`${this.BASE_URL}/search?query=${query}`);
  }

  getAllMovies(): Observable<MovieEntity[]> {
    return this.http.get<MovieEntity[]>(`${this.BASE_URL}/movies`);
  }
}

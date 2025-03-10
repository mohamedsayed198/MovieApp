import { Component, OnInit } from '@angular/core';
import { AdminService, MovieDTO, MovieEntity } from './admin.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['../shared/shared.component.css']
})
export class AdminComponent implements OnInit {
  searchQuery = '';
  searchResults: MovieDTO[] = [];
  localMovies: MovieEntity[] = [];

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.loadLocalMovies();
  }

  onSearch() {
    this.adminService.searchMovies(this.searchQuery).subscribe({
      next: (results) => {
        this.searchResults = results;
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  onAdd(movie: MovieDTO) {
    this.adminService.addMovie(movie).subscribe({
      next: (savedMovie) => {
        this.loadLocalMovies();
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  onDelete(movieId: number) {
    this.adminService.deleteMovie(movieId).subscribe({
      next: (msg) => {
        console.log(msg);
        this.loadLocalMovies();
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  loadLocalMovies() {
    this.adminService.listAllMovies().subscribe({
      next: (movies) => {
        this.localMovies = movies;
      },
      error: (err) => {
        console.error(err);
      }
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { UserService, MovieEntity } from './user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['../shared/shared.component.css']
})
export class UserComponent implements OnInit {
  searchQuery = '';
  searchResults: MovieEntity[] = [];
  localMovies: MovieEntity[] = [];

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.loadLocalMovies();
  }

  onSearch() {
    this.userService.searchMovies(this.searchQuery).subscribe({
      next: (results) => {
        this.searchResults = results;
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  loadLocalMovies() {
    this.userService.getAllMovies().subscribe({
      next: (movies) => {
        this.localMovies = movies;
      },
      error: (err) => {
        console.error(err);
      }
    });
  }
}

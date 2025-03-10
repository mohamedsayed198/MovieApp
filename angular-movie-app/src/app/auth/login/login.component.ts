import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  userName = '';
  password = '';
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    this.authService.login(this.userName, this.password).subscribe({
      next: (response) => {
        localStorage.setItem('token', response.token);

        if (this.userName === 'admin') {
          this.router.navigate(['/admin']);
        } else {
          this.router.navigate(['/user']);
        }
      },

      error: (err) => {
        console.error(err);
        this.errorMessage = 'Login failed. Check console.';
      }
    });
  }
}

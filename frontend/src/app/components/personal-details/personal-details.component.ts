import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { User } from '../../services/auth/user';
import { UserService } from '../../services/user/user.service';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-personal-details',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './personal-details.component.html',
  styleUrl: './personal-details.component.css'
})
export class PersonalDetailsComponent {
  errorMessage: string = "";
  user?:User;

  constructor(private userService:UserService) {
    this.userService.getUser(1).subscribe({
      next: (userData) => {
        this.user = userData;
      },
      error: (errorData) => {
        this.errorMessage = errorData;
      },
      complete: () => {
        console.log("User data Ok");
      }
    })
  }
}

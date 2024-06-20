import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginService } from '../../services/auth/login.service';

@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css'
})
export class NavComponent implements OnInit {

  userLoginOn:boolean=false;

  constructor(private LoginService:LoginService) { }

  ngOnInit(): void {
    this.LoginService.currentUserLoginOn.subscribe(
      {
        next:(userLoginOn) => {
          this.userLoginOn=userLoginOn;
        }
      }
    )
  }

  logout(){
    this.LoginService.logout();
  }

  

}

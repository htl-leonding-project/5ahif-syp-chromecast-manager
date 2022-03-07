import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/service/user.service';
import { UpdateUserComponent } from '../update-user/update-user.component';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  constructor(public userService: UserService,
    public router: AppRoutingModule,
    public routerx: Router) { }

  async ngOnInit(): Promise<void> {
    await this.userService.getUsers();
  }

  async onEdit(element : User): Promise<void>{
    UpdateUserComponent.id = element.id;
    UpdateUserComponent.oldName = element.name;
    UpdateUserComponent.oldPasswordHash = element.passwordHash;
  }

  async onDelete(element: User): Promise<void>{
    await this.userService.deleteUser(element.id);
    await this.userService.getUsers();
    alert('You deleted a room');
    
  }
}

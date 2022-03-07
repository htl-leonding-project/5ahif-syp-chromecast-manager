import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent implements OnInit {

  createUserForm!: FormGroup;

  constructor(private readonly formBuilder: FormBuilder,
    public userService: UserService,
    public router: Router) { }

  async ngOnInit(): Promise<void> {
      this.createUserForm = this.formBuilder.group({
        name: 'new Username',
        passwordHash: 'new PasswordHash'
      });
  }

  async onSave(): Promise<void>{
    const name = this.createUserForm.get('name')?.value;
    const passwordHash = this.createUserForm.get('passwordHash')?.value;

    const x = new User(0,name, passwordHash);

    alert('You added following Room: '+ x.name);

    await this.userService.postUser(x);

    await this.userService.getUsers();
    }
}
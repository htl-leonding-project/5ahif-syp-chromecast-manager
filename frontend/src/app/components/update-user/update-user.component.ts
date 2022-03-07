import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.scss']
})
export class UpdateUserComponent implements OnInit {
private static _id : number = 0
private static _oldName: string =""
private static _oldPasswordHash: string =""

public static get id() : number {
  return this._id;
}
public static set id(value: number){
  this._id = value;
}

public static get oldName() : string {
  return this._oldName;
}
public static set oldName(value : string) {
  this._oldName = value;
}
  
public static get oldPasswordHash():string {
  return this._oldPasswordHash;
}
public static set oldPasswordHash(value: string){
  this._oldPasswordHash = value;
}

  editUserForm!: FormGroup

  constructor(private readonly formBuilder: FormBuilder,
    public userService: UserService,
    public router: Router) { }

  async ngOnInit(): Promise<void> {
      this.editUserForm = this.formBuilder.group({
        id: UpdateUserComponent._id,
        name: UpdateUserComponent._oldName,
        passwordHash: UpdateUserComponent._oldPasswordHash
      });
  }

  async onSave(): Promise<void>{
    const id = this.editUserForm.get('id')?.value;
    const name = this.editUserForm.get('name')?.value;
    const passwordHash = this.editUserForm.get('passwordHash')?.value;

    const x = new User(id, name, passwordHash);

    alert('You added following Room: '+ x.name);

    await this.userService.putUser(x, UpdateUserComponent._id);

    await this.userService.getUsers();
  }

}

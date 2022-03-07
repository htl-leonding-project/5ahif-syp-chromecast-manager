import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Room } from 'src/app/model/room';
import { RoomsService } from 'src/app/service/room.service';

@Component({
  selector: 'app-update-room',
  templateUrl: './update-room.component.html',
  styleUrls: ['./update-room.component.scss']
})
export class UpdateRoomComponent implements OnInit {

  private static _id : number = 0
  private static _oldNumber: string =""
  private static _oldName: string =""

public static get id() : number {
  return this._id;
}

public static get oldNumber() : string {
  return this._oldNumber;
}
public static set oldNumber(value : string) {
  this._oldNumber = value;
}
  
public static get oldName():string {
  return this._oldName;
}
public static set oldName(value: string){
  this._oldName = value;
}

  editRoomForm!: FormGroup

  constructor(private readonly formBuilder: FormBuilder,
    public roomService: RoomsService,
    public router: Router) { }

  async ngOnInit(): Promise<void> {
      this.editRoomForm = this.formBuilder.group({
        number: UpdateRoomComponent._oldNumber,
        name: UpdateRoomComponent._oldName
      });
  }

  async onSave(): Promise<void>{
    const number = this.editRoomForm.get('number')?.value;
    const name = this.editRoomForm.get('name')?.value;

    const x = new Room(0, name, number);

    alert('You added following Room: '+ x.roomName);

    await this.roomService.putRoom(x, UpdateRoomComponent._oldName);

    await this.roomService.getRooms();
  }

}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Room } from 'src/app/model/room';
import { RoomsService } from 'src/app/service/room.service';

@Component({
  selector: 'app-create-room',
  templateUrl: './create-room.component.html',
  styleUrls: ['./create-room.component.scss']
})
export class CreateRoomComponent implements OnInit {

  createRoomForm!: FormGroup;

  constructor(private readonly formBuilder: FormBuilder,
    public roomService: RoomsService,
    public router: Router) { }

  async ngOnInit(): Promise<void> {
      this.createRoomForm = this.formBuilder.group({
        number: '0',
        name: 'new Room'
      });
  }

  async onSave(): Promise<void>{
    const number = this.createRoomForm.get('number')?.value;
    const name = this.createRoomForm.get('name')?.value;

    const x = new Room(0, name, number);


    await this.roomService.postRoom(x);

    await this.roomService.getRooms();
  }


}



import { Component, OnInit } from '@angular/core';
import { RoomsService } from 'src/app/service/rooms.service';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { Router } from '@angular/router';
import { Room } from 'src/app/model/room';
import { UpdateRoomComponent } from '../update-room/update-room.component';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.scss']
})
export class RoomsComponent implements OnInit {
  


  constructor(public roomsService: RoomsService,
    public router: AppRoutingModule,
    public routerx: Router) { }

  async ngOnInit(): Promise<void> {
    await this.roomsService.getRooms();
  }

  async onEdit(element : Room): Promise<void>{
    UpdateRoomComponent.oldName = element.roomName;
    UpdateRoomComponent.oldNumber = element.roomNumber;
  }

  async onDelete(element: Room): Promise<void>{
    await this.roomsService.deleteRoom(element.roomName);
    await this.roomsService.getRooms();
    alert('You deleted a room');
  }

}

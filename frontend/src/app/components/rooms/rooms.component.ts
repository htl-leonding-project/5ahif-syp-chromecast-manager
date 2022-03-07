import { Component, OnInit } from '@angular/core';
import { RoomsService } from 'src/app/service/room.service';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { Router } from '@angular/router';
import { Room } from 'src/app/model/room';
import { UpdateRoomComponent } from '../update-room/update-room.component';
import { RoomDetailsComponent } from '../room-details/room-details.component';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.scss']
})
export class RoomsComponent implements OnInit {
  
  constructor(public roomService: RoomsService,
    public router: AppRoutingModule,
    public routerx: Router) { }

  async ngOnInit(): Promise<void> {
    await this.roomService.getRooms();
  }

  public SetRoomIdInRoomDetails(id : number){
    RoomDetailsComponent.Id = id;
  }

  async onEdit(element : Room): Promise<void>{
    UpdateRoomComponent.oldName = element.roomName;
    UpdateRoomComponent.oldNumber = element.roomNumber;
  }

  async onDelete(element: Room): Promise<void>{
    await this.roomService.deleteRoom(element.roomName);
  }

}

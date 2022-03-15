import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InstallAtDto } from 'src/app/model/InstallAt';
import { InstallAtService } from 'src/app/service/install-at.service';
import { RoomsComponent } from '../rooms/rooms.component';

@Component({
  selector: 'app-room-details',
  templateUrl: './room-details.component.html',
  styleUrls: ['./room-details.component.scss']
})
export class RoomDetailsComponent implements OnInit {

  

  constructor(public installAtService: InstallAtService,
    public router: Router) { }

  async ngOnInit(): Promise<void> {
    await this.installAtService.getInstallAtsByRoomId(this.installAtService.roomId);
  }

  async onDeinstall(id: number): Promise<void>{
    alert(this.installAtService.roomId)

    await this.installAtService.deleteInstallAt(id);
    alert(this.installAtService.roomId)
    await this.installAtService.getInstallAtsByRoomId(this.installAtService.roomId);

  }

}

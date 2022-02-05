import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { DeviceService } from 'src/app/service/device.service';

@Component({
  selector: 'app-devices',
  templateUrl: './devices.component.html',
  styleUrls: ['./devices.component.scss']
})
export class DevicesComponent implements OnInit {
  constructor(public deviceService: DeviceService,
    public router: AppRoutingModule,
    public routerx: Router) { }

  async ngOnInit(): Promise<void> {
    await this.deviceService.getDevices();
  }
/*
  async onEdit(element : Room): Promise<void>{
    UpdateRoomComponent.oldName = element.roomName;
    UpdateRoomComponent.oldNumber = element.roomNumber;
  }

  async onDelete(element: Room): Promise<void>{
    await this.roomService.deleteRoom(element.roomName);
    await this.roomService.getRooms();
    alert('You deleted a room');
    */
}

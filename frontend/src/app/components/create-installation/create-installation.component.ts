import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Device } from 'src/app/model/device';
import { Room } from 'src/app/model/room';
import { User } from 'src/app/model/User';
import { DeviceService } from 'src/app/service/device.service';
import { InstallAtService } from 'src/app/service/install-at.service';
import { RoomsService } from 'src/app/service/room.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-create-installation',
  templateUrl: './create-installation.component.html',
  styleUrls: ['./create-installation.component.scss']
})
export class CreateInstallationComponent implements OnInit {

  createInstallForm!: FormGroup;

  selectedRoom!: Room;
  rooms!: any[];

  selectedDevice!: Device;
  devices!: any[];

  selectedUser!: User;
  users!: any[];

  constructor(private readonly formBuilder: FormBuilder,
    public installAtService: InstallAtService,
    public deviceService: DeviceService,
    public roomService: RoomsService,
    public userService: UserService,
    public router: Router) { }

  async ngOnInit(): Promise<void> {
    var roomsArr = await this.roomService.getRooms();
    this.convertRoomArr(roomsArr);

    var devicesArr: Device[] = await this.deviceService.getFreeDevices(); 
    this.convertDevArr(devicesArr);

    var usersArr: User[] = await this.userService.getUsers();
    this.
    
    createInstallForm = this.formBuilder.group({
      rooms: this.rooms,
      device: this.devices,
      user: this.users,
      installDate: Date.now
    })
  }

  public convertRoomArr(arr: Room[]):void{
    arr.forEach(element => {
      var currentRoom = {value: element, viewValue: element.roomNumber + ' ' + element.roomName}
      this.rooms.push(currentRoom)
    })
  }

  public convertDevArr(arr: Device[]): void{
    arr.forEach(element => {
      var currentDevice = {value: element, viewValue: element.name + ' ' + element.brand}
      this.devices.push(currentDevice)
    });
  }

  public convertUserArr(arr: User[]): void{
    arr.forEach(element =>{
      var currentUser = {value: element, viewValue: element.name}
      this.users.push(currentUser);
    })
  }

}
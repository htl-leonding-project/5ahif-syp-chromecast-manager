import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Device } from 'src/app/model/device';
import { DeviceDto } from 'src/app/model/DeviceDto';
import { InstallAtDto, InstallAtPostDto } from 'src/app/model/InstallAt';
import { Room } from 'src/app/model/room';
import { RoomDto } from 'src/app/model/RoomDto';
import { User } from 'src/app/model/User';
import { UserDto } from 'src/app/model/UserDto';
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

  selectedRoom!: string;
  rooms: RoomDto[] = [];

  selectedDevice!: string;
  devices: DeviceDto[] = [];

  selectedUser!: string;
  users: UserDto[] = [];

  installDate: Date = new Date(Date.now());

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
    this.convertUserArr(usersArr);

    this.createInstallForm = this.formBuilder.group({
      room: this.rooms,
      device: this.devices,
      user: this.users,
      installdate: 'heute',
      description: ''
    });
  }

  public convertRoomArr(arr: Room[]):void{
    arr.forEach(element => {
      if(element != null){
        var currentRoom = {value: element, viewValue: 'Nr.' + element.roomNumber + ' - ' + element.roomName};
        this.rooms.push(currentRoom);
      }
    })
  }

  public convertDevArr(arr: Device[]): void{
    arr.forEach(element => {
      if(element != null){
        var currentDevice = {value: element, viewValue: element.brand + ' ' + element.name};
        this.devices.push(currentDevice);
      }
    });
  }

  public convertUserArr(arr: User[]): void{
    arr.forEach(element =>{
      if(element != null){
        var currentUser = {value: element, viewValue: element.name}
        this.users.push(currentUser);
      }
    })
  }

  async onSave(): Promise<void>{
    const roomx : Room = this.createInstallForm.get('room')?.value;
    const devicex : Device = this.createInstallForm.get('device')?.value;
    const userx : User = this.createInstallForm.get('user')?.value;
    const descriptionx : string = this.createInstallForm.get('description')?.value;

    alert('room: ' + roomx.roomName + ' device: ' + devicex + ' user: ' + userx + ' description: ' + descriptionx + ' installDate: ' + this.installDate.toDateString())

    const postInstallAt : InstallAtPostDto = {id: 0, installDate: this.installDate, removeDate: this.installDate , description: descriptionx, u_id: userx.id, r_id: roomx.id, d_id: devicex.id};

    //JSON.stringify({ name: "bob", age: 34, created: new Date() });
  //'{"name":"bob","age":34,"created":"2016-03-19T18:15:12.710Z"}'

    await this.installAtService.postInstallAt(postInstallAt);

    await this.installAtService.getInstallAts();
  }

}
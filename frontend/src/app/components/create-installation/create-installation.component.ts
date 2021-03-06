import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { CategoryDto } from 'src/app/model/CategoryDto';
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
  public createInstallForm!: FormGroup;

  selectedRoom!: Room;
  rooms: RoomDto[] = [];

  selectedDevice!: Device;
  devices: DeviceDto[] = [];

  selectedUser!: User;
  users: UserDto[] = [];

  selectedCategory!: string;
  categories: CategoryDto[] = []

  installDate : Date = new Date(Date.now());
  installDateStr = new Date(Date.now()).toLocaleDateString();

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

    //var catArr: string[] = await this.deviceService.getAllCategories(this.selectedCategory);
    //this.convertCatArr(catArr);

    /*var catArr: string[] = await this.deviceService.getAllCategories(this.selectedCategory);
    this.convertCatArr(catArr);*/

    this.createInstallForm = this.formBuilder.group({
      room: null,
      device: null,
      user: null,
      installdate: '',
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

  public convertCatArr(arr: string[]):void {
    arr.forEach(element => {
      if(element != null){
        var currentCategory = {value: element, viewValue : element}
        this.categories.push(currentCategory)
      }
    })
  }

  async onSave(): Promise<void>{
    /*const roomx : Room = this.createInstallForm.get('room')?.value;
    const devicex : Device = this.createInstallForm.get('device')?.value;
    const userx : User = this.createInstallForm.get('user')?.value;
    */
    const descriptionx : string = this.createInstallForm.get('description')?.value;
    const getInstallDatex: string  = this.createInstallForm.get('installdate')?.value;

    const postInstallAt : InstallAtPostDto = new InstallAtPostDto (getInstallDatex, getInstallDatex, descriptionx, this.selectedUser.id.toString(), this.selectedRoom.id.toString(), this.selectedDevice.id.toString() );

    //JSON.stringify({ name: "bob", age: 34, created: new Date() });
  //'{"name":"bob","age":34,"created":"2016-03-19T18:15:12.710Z"}'

    await this.installAtService.postInstallAt(postInstallAt);

    await this.installAtService.getInstallAts();
  }

}

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoomsComponent } from './components/rooms/rooms.component';
import { AppComponent } from './app.component';
import { CreateRoomComponent } from './components/create-room/create-room.component';
import { UpdateRoomComponent } from './components/update-room/update-room.component';
import { DevicesComponent } from './components/devices/devices.component';
import { CreateDeviceComponent } from './components/create-device/create-device.component';
import { UpdateDeviceComponent } from './components/update-device/update-device.component';
import { UsersComponent } from './components/users/users.component';
import { CreateUserComponent } from './components/create-user/create-user.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';
import { RoomDetailsComponent } from './components/room-details/room-details.component';
import { InstallsComponent } from './components/installs/installs.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'rooms'},
  {path: 'rooms', component: RoomsComponent},
  {path: 'create-room', component: CreateRoomComponent},
  {path: 'update-room', component: UpdateRoomComponent},
  {path: 'devices', component: DevicesComponent},
  {path: 'create-device', component: CreateDeviceComponent},
  {path: 'update-device', component: UpdateDeviceComponent},
  {path: 'users', component: UsersComponent},
  {path: 'create-user', component: CreateUserComponent},
  {path: 'update-user', component: UpdateUserComponent},
  {path: 'room-details', component: RoomDetailsComponent},
  {path: 'install', component: InstallsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

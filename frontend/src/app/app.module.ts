import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RoomsComponent } from './components/rooms/rooms.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from './material-module';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { HttpClientModule } from '@angular/common/http';
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


@NgModule({
  declarations: [
    AppComponent,
    RoomsComponent,
    CreateRoomComponent,
    UpdateRoomComponent,
    DevicesComponent,
    CreateDeviceComponent,
    UpdateDeviceComponent,
    UsersComponent,
    CreateUserComponent,
    UpdateUserComponent,
    RoomDetailsComponent,
    InstallsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MaterialModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

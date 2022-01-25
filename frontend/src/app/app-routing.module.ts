import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoomsComponent } from './components/rooms/rooms.component';
import { AppComponent } from './app.component';
import { CreateRoomComponent } from './components/create-room/create-room.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'rooms'},
  {path: 'rooms', component: RoomsComponent},
  {path: 'create-room', component: CreateRoomComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

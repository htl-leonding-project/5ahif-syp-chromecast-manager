import { Component, OnInit } from '@angular/core';
import { RoomsService } from 'src/app/service/rooms.service';
import { AppRoutingModule } from 'src/app/app-routing.module';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.scss']
})
export class RoomsComponent implements OnInit {
  
  constructor(public roomsService: RoomsService,
    public router: AppRoutingModule) { }

  async ngOnInit(): Promise<void> {
    await this.roomsService.getRooms();
  }

}

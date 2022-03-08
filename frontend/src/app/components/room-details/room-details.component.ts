import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InstallAtDto } from 'src/app/model/InstallAt';
import { InstallAtService } from 'src/app/service/install-at.service';

@Component({
  selector: 'app-room-details',
  templateUrl: './room-details.component.html',
  styleUrls: ['./room-details.component.scss']
})
export class RoomDetailsComponent implements OnInit {

  private static _id : number = 0;

  public static get Id() : number {
    return this._id;
  }

  public static set Id(value : number){
    this._id = value;
  }

  constructor(public installAtService: InstallAtService,
    public router: Router) { }

  async ngOnInit(): Promise<void> {
    await this.installAtService.getInstallAtById(RoomDetailsComponent.Id);
  }

  async onDeinstall(id: number): Promise<void>{
    await this.installAtService.deleteInstallAt(id);
  }

}

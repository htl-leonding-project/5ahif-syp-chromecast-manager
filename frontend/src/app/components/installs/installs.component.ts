import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InstallAt } from 'src/app/model/InstallAt';
import { InstallAtService } from 'src/app/service/install-at.service';

@Component({
  selector: 'app-installs',
  templateUrl: './installs.component.html',
  styleUrls: ['./installs.component.scss']
})
export class InstallsComponent implements OnInit {
  constructor(public installAtService: InstallAtService,
    public router: Router) { }

  async ngOnInit(): Promise<void> {
    await this.installAtService.getInstallAts();
  }

  async onDeinstall(id: number): Promise<void>{
    await this.installAtService.deleteInstallAt(id);
  }

}

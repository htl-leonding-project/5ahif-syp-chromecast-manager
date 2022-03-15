import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { Device } from 'src/app/model/device';
import { DeviceService } from 'src/app/service/device.service';
import { UpdateDeviceComponent } from '../update-device/update-device.component';

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

  async onEdit(element : Device): Promise<void>{
    UpdateDeviceComponent.id = element.id;
    UpdateDeviceComponent.oldName = element.name;
    UpdateDeviceComponent.oldBrand = element.brand;
  }

  async onDelete(element: Device): Promise<void>{
    await this.deviceService.deleteDevice(element.id);
    await this.deviceService.getDevices();
  }
}
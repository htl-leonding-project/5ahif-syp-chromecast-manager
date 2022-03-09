import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Device } from 'src/app/model/device';
import { DeviceService } from 'src/app/service/device.service';
import { InstallAtService } from 'src/app/service/install-at.service';

@Component({
  selector: 'app-create-installation',
  templateUrl: './create-installation.component.html',
  styleUrls: ['./create-installation.component.scss']
})
export class CreateInstallationComponent implements OnInit {

  createInstallForm!: FormGroup;

  selectedDevice!: Device;
  devices!: any[];

  constructor(private readonly formBuilder: FormBuilder,
    public installAtService: InstallAtService,
    public deviceService: DeviceService,
    public router: Router) { }

  async ngOnInit(): Promise<void> {
    //this.devices = this.deviceService.getFreeDevices(); 
    this.createInstallForm = this.formBuilder.group({
      device: this.devices,
    })
  }

}

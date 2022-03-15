import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Device } from 'src/app/model/device';
import { DeviceService } from 'src/app/service/device.service';

@Component({
  selector: 'app-create-device',
  templateUrl: './create-device.component.html',
  styleUrls: ['./create-device.component.scss']
})
export class CreateDeviceComponent implements OnInit {

  createDeviceForm!: FormGroup;

  constructor(private readonly formBuilder: FormBuilder,
    public deviceService: DeviceService,
    public router: Router) { }

  async ngOnInit(): Promise<void> {
      this.createDeviceForm = this.formBuilder.group({
        name: 'new Device',
        brand: 'new Brand'
      });
  }

  async onSave(): Promise<void>{
    const name = this.createDeviceForm.get('name')?.value;
    const brand = this.createDeviceForm.get('brand')?.value;

    const x = new Device(0,name, brand);


    await this.deviceService.postDevice(x);

    await this.deviceService.getDevices();
  }


}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Device } from 'src/app/model/device';
import { DeviceService } from 'src/app/service/device.service';

@Component({
  selector: 'app-update-device',
  templateUrl: './update-device.component.html',
  styleUrls: ['./update-device.component.scss']
})
export class UpdateDeviceComponent implements OnInit {
private static _id : number = 0
private static _oldName: string =""
private static _oldBrand: string =""

public static get id() : number {
  return this._id;
}
public static set id(value: number){
  this._id = value;
}

public static get oldName() : string {
  return this._oldName;
}
public static set oldName(value : string) {
  this._oldName = value;
}
  
public static get oldBrand():string {
  return this._oldBrand;
}
public static set oldBrand(value: string){
  this._oldBrand = value;
}

  editDeviceForm!: FormGroup

  constructor(private readonly formBuilder: FormBuilder,
    public deviceService: DeviceService,
    public router: Router) { }

  async ngOnInit(): Promise<void> {
      this.editDeviceForm = this.formBuilder.group({
        id: UpdateDeviceComponent._id,
        name: UpdateDeviceComponent._oldName,
        brand: UpdateDeviceComponent._oldBrand
      });
  }

  async onSave(): Promise<void>{
    const id = this.editDeviceForm.get('id')?.value;
    const name = this.editDeviceForm.get('name')?.value;
    const brand = this.editDeviceForm.get('brand')?.value;

    const x = new Device(id, name, brand);


    await this.deviceService.putDevice(x, UpdateDeviceComponent._id);

    await this.deviceService.getDevices();
  }

}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { CategoryDto } from 'src/app/model/CategoryDto';
import { Device } from 'src/app/model/device';
import { DeviceService } from 'src/app/service/device.service';

@Component({
  selector: 'app-create-device',
  templateUrl: './create-device.component.html',
  styleUrls: ['./create-device.component.scss']
})
export class CreateDeviceComponent implements OnInit {
  selectedCategory!: string;
  categories: CategoryDto[] = []

  createDeviceForm!: FormGroup;

  constructor(private readonly formBuilder: FormBuilder,
    public deviceService: DeviceService,
    public router: Router) { }

  async ngOnInit(): Promise<void> {
    var catArr: string[] = await this.deviceService.getAllCategories();
    this.convertCatArr(catArr);

    this.createDeviceForm = this.formBuilder.group({
      name: 'new Device',
      brand: 'new Brand',
      ean: 'new EAN',
      category: null
    });
  }

  public convertCatArr(arr: string[]):void {
    arr.forEach(element => {
      if(element != null){
        var currentCategory = {value: element, viewValue : element}
        this.categories.push(currentCategory)
      }
    })
  }

  async onSave(): Promise<void>{
    const name = this.createDeviceForm.get('name')?.value;
    const brand = this.createDeviceForm.get('brand')?.value;
    const ean = this.createDeviceForm.get('ean')?.value;

    const x = new Device(0,name, brand, ean, this.selectedCategory);


    await this.deviceService.postDevice(x);

    await this.deviceService.getDevices();
  }


}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { Device } from 'src/app/model/device';
import { DeviceService } from 'src/app/service/device.service';
import { UpdateDeviceComponent } from '../update-device/update-device.component';
import { CategoryDto} from '../../model/CategoryDto'
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-devices',
  templateUrl: './devices.component.html',
  styleUrls: ['./devices.component.scss']
})

export class DevicesComponent implements OnInit {
  public deviceForm!: FormGroup;
  selectedCategory!: string;
  categories: CategoryDto[] = []

  constructor(private readonly formBuilder: FormBuilder,
    public deviceService: DeviceService,
    public router: AppRoutingModule,
    public routerx: Router) { }

  async ngOnInit(): Promise<void> {
    var catArr: string[] = await this.deviceService.getAllCategories();
    this.convertCatArr(catArr);
    await this.deviceService.getDevices();
    this.deviceForm = this.formBuilder.group({
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

  async onEdit(element : Device): Promise<void>{
    UpdateDeviceComponent.id = element.id;
    UpdateDeviceComponent.oldName = element.name;
    UpdateDeviceComponent.oldBrand = element.brand;
    UpdateDeviceComponent.oldEan = element.ean;
    UpdateDeviceComponent.oldCategory = element.category;
  }

  async onDelete(element: Device): Promise<void>{
    await this.deviceService.deleteDevice(element.id);
    await this.deviceService.getDevices();
  }

  public setCategory(): void{
    this.deviceService.getByCategory(this.selectedCategory)
  }
}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { Device } from 'src/app/model/device';
import { DeviceService } from 'src/app/service/device.service';
import { UpdateDeviceComponent } from '../update-device/update-device.component';
import { CategoryDto} from '../../model/CategoryDto'

@Component({
  selector: 'app-devices',
  templateUrl: './devices.component.html',
  styleUrls: ['./devices.component.scss']
})

export class DevicesComponent implements OnInit {
  selectedCategory!: string;
  categories: CategoryDto[] = []

  constructor(public deviceService: DeviceService,
    public router: AppRoutingModule,
    public routerx: Router) { }

  async ngOnInit(): Promise<void> {
    var catArr: string[] = await this.deviceService.getAllCategories(this.selectedCategory); 
    this.convertCatArr(catArr);
    await this.deviceService.getDevices();
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
  }

  async onDelete(element: Device): Promise<void>{
    await this.deviceService.deleteDevice(element.id);
    await this.deviceService.getDevices();
  }

  public setCategory(): void{
    this.deviceService.getByCategory(this.selectedCategory)
  }
}
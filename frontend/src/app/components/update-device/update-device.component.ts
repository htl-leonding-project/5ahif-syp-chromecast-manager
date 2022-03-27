import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { CategoryDto } from 'src/app/model/CategoryDto';
import { Device } from 'src/app/model/device';
import { DeviceService } from 'src/app/service/device.service';

@Component({
  selector: 'app-update-device',
  templateUrl: './update-device.component.html',
  styleUrls: ['./update-device.component.scss']
})
export class UpdateDeviceComponent implements OnInit {
  selectedCategory!: string;
  categories: CategoryDto[] = []

  private static _id : number = 0
  private static _oldName: string =""
  private static _oldBrand: string =""
  private static _oldEan: string =""
  private static _oldCategory: string ="" 

  public static get oldCategory(): string{
    return this._oldCategory;
  }

  public static set oldCategory(value: string){
    this._oldCategory = value;
  }

  public static get oldEan(): string{
    return this._oldEan;
  }

  public static set oldEan(value: string){
    this._oldEan = value;
  }

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
    this.selectedCategory = UpdateDeviceComponent._oldCategory;
    var catArr: string[] = await this.deviceService.getAllCategories();
    this.convertCatArr(catArr);
  

      this.editDeviceForm = this.formBuilder.group({
        id: UpdateDeviceComponent._id,
        name: UpdateDeviceComponent._oldName,
        brand: UpdateDeviceComponent._oldBrand,
        ean: UpdateDeviceComponent._oldEan,
        category: UpdateDeviceComponent._oldCategory
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
    const id = this.editDeviceForm.get('id')?.value;
    const name = this.editDeviceForm.get('name')?.value;
    const brand = this.editDeviceForm.get('brand')?.value;
    const ean = this.editDeviceForm.get('ean')?.value;

    const x = new Device(id, name, ean, brand, '');

    await this.deviceService.putDevice(x, UpdateDeviceComponent._id);

    await this.deviceService.getDevices();
  }

}

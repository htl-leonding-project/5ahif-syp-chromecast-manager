import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Device } from '../model/device';

@Injectable({
  providedIn: 'root'
})
export class DeviceService {

  displayedColumns: string[] = ['id','name','brand','editDevice','deleteDevice'];
  datasource: MatTableDataSource<Device> = new MatTableDataSource();
  
  url: string;
  
  constructor(private httpClient: HttpClient) { 
    this.url = 'http://localhost:8080/api';
  }

  public async getDevices(): Promise<Device[]>{
    const data: Device[]  = await this.httpClient.get<Device[]>(`${this.url}/devices`).toPromise();
    //console.log(x)
    return this.datasource.data = data;

  }

  public async postDevice(device : Device): Promise<void>{
      const myheader = new HttpHeaders().set('content-type', 'application/json')
      const body = { name: device.name,
                     brand: device.brand};
      console.log(JSON.stringify(device));            
      this.httpClient.post<any>(this.url + '/create-device', JSON.stringify(device), {
        headers: myheader
        }).subscribe();
      
        this.getDevices();
  }
  
  async putDevice(device : Device, id: number): Promise<void> {
    const myheader = new HttpHeaders().set('content-type', 'application/json')
    const body = { 
                   name: device.name,
                   brand: device.brand};
    console.log(JSON.stringify(device));            
    this.httpClient.put<any>(this.url + '/update-device/' + id, JSON.stringify(device), {
      headers: myheader
      }).subscribe();
    await this.getDevices();
    
    window.location.reload();
  }

  deleteDevice(id : number) {
    const myheader = new HttpHeaders().set('content-type', 'application/json')
  
    this.httpClient.delete<Device>(this.url + '/delete-device/' + id, {
      headers: myheader
    }).subscribe();
  }
  
  public async reloadCurrentWindow(){
    await this.sleep(10);

    window.location.reload();
  }

  public sleep(milliseconds:number) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
      if ((new Date().getTime() - start) > milliseconds){
        break;
      }
    }
  }
}

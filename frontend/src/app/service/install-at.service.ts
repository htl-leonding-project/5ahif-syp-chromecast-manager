import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SELECT_PANEL_INDENT_PADDING_X } from '@angular/material/select/select';
import { MatTableDataSource } from '@angular/material/table';
import { InstallAt } from '../model/InstallAt';
import { DataSource } from '@angular/cdk/collections';

@Injectable({
  providedIn: 'root'
})
export class InstallAtService {
  displayedColumns: string[] = ['deviceName','deviceBrand', 'installedFrom', 'installDate','deinstall'];
  datasource: MatTableDataSource<InstallAt> = new MatTableDataSource();
  roomId: number = 0;
  url: string;
  
  constructor(private httpClient: HttpClient) { 
    this.url = 'http://localhost:8080/install';
  }

  /*public async getInstallAts(): Promise<InstallAt[]>{
    const data: InstallAt[] = await this.httpClient.get<InstallAt[]>() {}
  }*/

  public async getInstallAtById(roomId: number): Promise<InstallAt[]> {
    this.roomId = roomId;
    const data: InstallAt[] = await this.httpClient.get<InstallAt[]>(`${this.url}/room/${roomId}`).toPromise();
    console.log('InstallAt Id: ' + data[0].id + ' InstallAtDeviceName: ' + data[0].deviceName );
    return this.datasource.data = data;
  }

  deleteInstallAt(installAtId: number) {
    const myheader = new HttpHeaders().set('content-type', 'application/json');

    this.httpClient.delete<InstallAt>(this.url + '/delete/' + installAtId, {
      headers: myheader
    }).subscribe();

    this.getInstallAtById(this.roomId);
  }

}

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SELECT_PANEL_INDENT_PADDING_X } from '@angular/material/select/select';
import { MatTableDataSource } from '@angular/material/table';
import { InstallAt, InstallAtDto, InstallAtPostDto } from '../model/InstallAt';
import { DataSource } from '@angular/cdk/collections';

@Injectable({
  providedIn: 'root'
})
export class InstallAtService {
  displayedColumns: string[] = ['deviceName','deviceBrand', 'installedFrom', 'installDate','deinstall'];
  displayedColumnsx: string[] = ['id', 'roomNumber', 'roomName', 'deviceName','deviceBrand', 'installedFrom', 'installDate','deinstall'];

  datasource: MatTableDataSource<InstallAtDto> = new MatTableDataSource();
  datasourcex: MatTableDataSource<InstallAt> = new MatTableDataSource();
  roomId: number = 0;
  url: string;
  
  constructor(private httpClient: HttpClient) { 
    this.url = 'http://localhost:8080/install';
  }

  public async getInstallAts(): Promise<InstallAt[]>{
    const data: InstallAt[] = await this.httpClient.get<InstallAt[]>(this.url + '/installs').toPromise()
    return this.datasourcex.data = data;
  }

  public async getInstallAtsByRoomId(roomId: number): Promise<InstallAtDto[]> {
    await this.sleep(10);

    this.roomId = roomId;
    const data: InstallAtDto[] = await this.httpClient.get<InstallAtDto[]>(`${this.url}/room/${roomId}`).toPromise();
    console.log('InstallAt Id: ' + data[0].id + ' InstallAtDeviceName: ' + data[0].deviceName );
    return this.datasource.data = data;
  }

  public async postInstallAt(postInstallAt: InstallAtPostDto):Promise<void> {
    const myheader = new HttpHeaders().set('content-type', 'application/json');
  
    alert(JSON.stringify(postInstallAt))
    this.httpClient.post<any>(this.url + '/create-installat', JSON.stringify(postInstallAt),{
      headers: myheader
    }).subscribe();
  }

  deleteInstallAt(installAtId: number) {
    this.sleep(10)
    const myheader = new HttpHeaders().set('content-type', 'application/json');

    this.httpClient.delete<InstallAtDto>(this.url + '/delete/' + installAtId, {
      headers: myheader
    }).subscribe();

    this.getInstallAtsByRoomId(this.roomId);
    this.reloadCurrentWindow();
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

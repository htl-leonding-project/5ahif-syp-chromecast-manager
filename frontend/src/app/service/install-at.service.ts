import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { InstallAt } from '../model/InstallAt';

@Injectable({
  providedIn: 'root'
})
export class InstallAtService {
  displayedColumns: string[] = ['deviceName','deviceBrand', 'installedFrom', 'installDate'];
  datasource: MatTableDataSource<InstallAt> = new MatTableDataSource();

  url: string;
  
  constructor(private httpClient: HttpClient) { 
    this.url = 'http://localhost:8080/api';
  }

  public async getInstallAts(roomId: number): Promise<InstallAt[]> {
    const data: InstallAt[] = await this.httpClient.get<InstallAt[]>(`${this.url}//installAt/room/this.roomId`).toPromise();
    return this.datasource.data = data;
  }

  deleteInstallAt() {
    throw new Error('Method not implemented.');
  }

}

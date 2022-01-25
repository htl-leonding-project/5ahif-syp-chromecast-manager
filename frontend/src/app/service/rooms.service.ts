import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import {Room} from '../model/room';
import { DataSource } from '@angular/cdk/collections';

@Injectable({
  providedIn: 'root'
})
export class RoomsService {
  displayedColumns: string[] = ['roomName','roomNumber'];
  datasource: MatTableDataSource<Room> = new MatTableDataSource();
  
  url: string;
  
  constructor(private httpClient: HttpClient) { 
    this.url = 'http://localhost:8080/api';
  }

  async getRooms(): Promise<Room[]>{
    const data: Room[]  = await this.httpClient.get<Room[]>(`${this.url}/rooms`).toPromise();
    //console.log(x)
    return this.datasource.data = data;
  }
}

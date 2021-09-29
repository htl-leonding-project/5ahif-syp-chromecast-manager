import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import {Room} from '../model/room';
import { DataSource } from '@angular/cdk/collections';

@Injectable({
  providedIn: 'root'
})
export class RoomsService {
  displayedColumns: string[] = ['room','class'];
  datasource: MatTableDataSource<Room> = new MatTableDataSource()
  
  rooms: MatTableDataSource<Room> = new MatTableDataSource();
  url: string;
  
  constructor(private httpClient: HttpClient) { 
    this.url = 'http://localhost:2000'
  }

  async getRooms(): Promise<Room[]>{
    const data: Room[] = await this.httpClient.get<Room[]>(`${this.url}/rooms`).toPromise();
    return this.rooms.data = data;
  }
}

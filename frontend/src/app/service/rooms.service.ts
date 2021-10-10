import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import {Room} from '../model/room';
import { DataSource } from '@angular/cdk/collections';

@Injectable({
  providedIn: 'root'
})
export class RoomsService {
  displayedColumns: string[] = ['id','roomnumber','roomname'];
  datasource: MatTableDataSource<Room> = new MatTableDataSource();
  
  url: string;
  
  constructor(private httpClient: HttpClient) { 
    this.url = 'http://localhost:8080';
  }

  async getRooms(): Promise<Room[]>{
    const x: Room[]  = await this.httpClient.get<Room[]>(`${this.url}/rooms`).toPromise();
    const data: Room[] = x.map(room => new Room(room.id, room.roomnumber, room.roomname));
    return this.datasource.data = data;
  }
}

import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import {Room} from '../model/room';
import { DataSource } from '@angular/cdk/collections';

@Injectable({
  providedIn: 'root'
})
export class RoomsService {
  displayedColumns: string[] = ['roomName','roomNumber', 'editRoom', 'deleteRoom'];
  datasource: MatTableDataSource<Room> = new MatTableDataSource();
  
  url: string;
  
  constructor(private httpClient: HttpClient) { 
    this.url = 'http://localhost:8080/api';
  }

  public async getRooms(): Promise<Room[]>{
    const data: Room[]  = await this.httpClient.get<Room[]>(`${this.url}/rooms`).toPromise();
    //console.log(x)
    return this.datasource.data = data;
  }

  async postRoom(room : Room): Promise<void>{
      const myheader = new HttpHeaders().set('content-type', 'application/json')
      const body = { roomNumber: room.roomNumber,
                     roomName: room.roomName};
      console.log(JSON.stringify(room));            
      this.httpClient.post<any>(this.url + '/create', JSON.stringify(room), {
        headers: myheader
        }).subscribe();
  }
  
  async putRoom(room : Room, oldRoomName: string): Promise<void> {
    const myheader = new HttpHeaders().set('content-type', 'application/json')
    const body = { roomNumber: room.roomNumber,
                   roomName: room.roomName};
    console.log(JSON.stringify(room));            
    this.httpClient.put<any>(this.url + '/update/' + oldRoomName, JSON.stringify(room), {
      headers: myheader
      }).subscribe();
  }

  deleteRoom(zName : string) {
    const myheader = new HttpHeaders().set('content-type', 'application/json')
  
    this.httpClient.delete<Room>(this.url + '/delete/' + zName, {
      headers: myheader
    }).subscribe();
    alert('Delete Endpoint ausgeführt');
  }
  
}

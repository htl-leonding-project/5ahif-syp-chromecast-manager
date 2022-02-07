import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { User } from '../model/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  displayedColumns: string[] = ['name','editUser','deleteUser'];
  datasource: MatTableDataSource<User> = new MatTableDataSource();
  
  url: string;
  
  constructor(private httpClient: HttpClient) { 
    this.url = 'http://localhost:8080/api';
  }

  public async getUsers(): Promise<User[]>{
    await this.sleep(10);

    const data: User[]  = await this.httpClient.get<User[]>(`${this.url}/users`).toPromise();
    //console.log(x)
    return this.datasource.data = data;
  }

  async postUser(user : User): Promise<void>{
    const myheader = new HttpHeaders().set('content-type', 'application/json')
    const body = { name: user.name,
                   passwordHash: user.passwordHash};
    console.log(JSON.stringify(user));            
    this.httpClient.post<any>(this.url + '/create-user', JSON.stringify(user), {
      headers: myheader
      }).subscribe();

    this.getUsers();
  }

  async putUser(user : User, oldUserId: number): Promise<void> {
    const myheader = new HttpHeaders().set('content-type', 'application/json')
    const body = { name: user.name,
                   user: user.passwordHash};
    console.log(JSON.stringify(user));            
    this.httpClient.put<any>(this.url + '/update-user/' + oldUserId, JSON.stringify(user), {
      headers: myheader
      }).subscribe();

    this.getUsers();
  }

  deleteUser(id : number) {
    const myheader = new HttpHeaders().set('content-type', 'application/json')
  
    this.httpClient.delete<User>(this.url + '/delete-user/' + id, {
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

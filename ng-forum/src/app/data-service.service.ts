import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "./model/user";


@Injectable()
export class DataService{

  constructor(public http: HttpClient){

  }

  fetchUsers(): Promise<User[]> {

    return this.http
      .get('http://localhost:8080/forum/api/users')
      .toPromise()
      .then(data => data as User[])
  }

  createUser(user: User) {
    let url = ('http://localhost:8080/forum/api/users')
    // dto = data transfer object : aux petits oignons pour Jax B
    let dto = {
      name: user.name,
      id: user.id
    };

    console.log('sending user:', dto);

    return this.http.post(url, dto)
      .toPromise()
      .then(data => console.log('Success :)', data))
    //.catch (e => console.error('Fail :(', e));

  }

}

import { Component, OnInit } from '@angular/core';
import {User} from "../model/user";
import {DataService} from "../data-service.service";

@Component({
  selector: 'app-user-list-view',
  templateUrl: './user-list-view.component.html',
  styleUrls: ['./user-list-view.component.css']
})
export class UserListViewComponent implements OnInit {

  users: User[];
  formModel = {
    name :'',
    limit:1000,
    level: 'Noob - 0'
  };

  levels = ['Noob', 'Expert', 'Master'];

  constructor(public dataService: DataService) {
    dataService.fetchUsers()
      .then(users => this.users = users)
      .then(users => console.log('Users:', users));
  }

  ngOnInit() {
  }



}

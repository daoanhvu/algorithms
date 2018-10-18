import { Component, OnInit } from '@angular/core';

import { User } from '@app/models';

@Component({
    selector: 'app-group',
    styleUrls: ['./group.component.scss'],
    templateUrl: './group.component.html'
})
export class GroupComponent implements OnInit {
    allMembers: User[] = [];
    filteredUsers: User[] = [];

    constructor() {

    }

    ngOnInit() {

    }
}

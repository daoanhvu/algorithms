import { Component, OnInit, Input } from '@angular/core';

import { User } from '@app/models';
@Component({
    selector: 'app-group-member-list',
    styleUrls: ['./memberlist.component.scss'],
    templateUrl: './memberlist.component.html'
})
export class MemberListComponent implements OnInit {

    displayedColumns: string[] = [ 'FirstName', 'LastName', 'Username', 'Phone', 'Role' ];
    @Input()
    members: User[] = [];
    constructor() {

    }

    ngOnInit() {

    }
}

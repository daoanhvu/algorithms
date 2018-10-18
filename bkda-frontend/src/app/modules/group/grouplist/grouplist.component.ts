import { Component, OnInit, Input } from '@angular/core';

import { User } from '@app/models';
@Component({
    selector: 'app-group-list',
    styleUrls: ['./grouplist.component.scss'],
    templateUrl: './grouplist.component.html'
})
export class GroupListComponent implements OnInit {
    @Input()
    members: User[] = [];
    constructor() {

    }

    ngOnInit() {

    }
}

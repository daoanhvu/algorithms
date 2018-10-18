import { Component, OnInit, Input } from '@angular/core';

import { User } from '@app/models';
@Component({
    selector: 'app-group-member-list',
    styleUrls: ['./memberlist.component.scss'],
    templateUrl: './memberlist.component.html'
})
export class MemberListComponent implements OnInit {
    @Input()
    members: User[] = [];
    constructor() {

    }

    ngOnInit() {

    }
}

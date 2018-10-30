import { Component, OnInit, Input } from '@angular/core';

import { Group } from '@app/models';
@Component({
    selector: 'app-group-list',
    styleUrls: ['./grouplist.component.scss'],
    templateUrl: './grouplist.component.html'
})
export class GroupListComponent implements OnInit {

    displayedColumns: string[] = [ 'GroupName' ];
    @Input()
    groups: Group[] = [];
    constructor() {

    }

    ngOnInit() {

    }
}

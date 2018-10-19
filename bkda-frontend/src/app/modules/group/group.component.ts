import { Component, OnInit } from '@angular/core';

import { User, Group } from '@app/models';
import { UserService } from '@app/services/user.service';
import { JwtService } from '@app/services/jwt.service';
import { PageEvent } from '@angular/material';

@Component({
    selector: 'app-group',
    styleUrls: ['./group.component.scss'],
    templateUrl: './group.component.html'
})
export class GroupComponent implements OnInit {
    group: Group;
    allMembers: User[] = [];
    filteredUsers: User[] = [];

    pageEvent: PageEvent;

    constructor(
        private jwtService: JwtService,
        private userService: UserService) {

    }

    ngOnInit() {
        this.pageEvent = new PageEvent();
        this.userService.loadMemberList(this.jwtService.getCredentials().userid, 0, 25)
            .subscribe(
                (res: any) => {

            });
    }
}

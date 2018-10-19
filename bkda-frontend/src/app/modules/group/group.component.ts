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
    groups: Group[] = [];
    group: Group;
    allMembers: User[] = [];
    filteredUsers: User[] = [];

    pageEvent: PageEvent;

    constructor(
        private jwtService: JwtService,
        private userService: UserService) {
        this.createFakeData();
    }

    private createFakeData() {
        this.group = {
            description: 'Chao mung cac ban den voi nhom cua chung toi',
            name: 'Fake Group',
            owner: {
                userId: 2,
                username: 'admin@bkda.com',
                firstname: 'Admin',
                lastname: 'System',
                phone: '123456'
            }
        };
        this.groups.push(this.group);
    }

    ngOnInit() {
        // this.pageEvent = new PageEvent();
        // this.userService.loadMemberList(this.jwtService.getCredentials().userid, 0, 25)
        //     .subscribe(
        //         (res: any) => {

        //     });
    }
}

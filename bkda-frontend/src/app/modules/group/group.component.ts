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

    memberListPage = 0;
    memberListPageSize = 25;

    constructor(
        private jwtService: JwtService,
        private userService: UserService) {
        // this.createFakeData();
    }

    private createFakeData() {
        this.group = {
            id: 1,
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
        this.userService.getOwnGroup().subscribe((res: Group) => {
            this.group = res;
            this.doFilter();
        });
    }

    onMemberListPageChange(event: PageEvent) {
        this.userService.loadMemberList(this.group.id, event.pageIndex, event.pageSize).subscribe(
            (res: any) => {
                this.allMembers = res;
            });
    }

    doFilter() {
        const pageEvent = new PageEvent();
        pageEvent.pageSize = 25;
        pageEvent.pageIndex = 0;
        this.onMemberListPageChange(pageEvent);
    }
}

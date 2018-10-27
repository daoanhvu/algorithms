import { Component, OnInit } from '@angular/core';

import { User, Group, GroupMember } from '@app/models';
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
    allMembers: GroupMember[] = [];
    filteredUsers: GroupMember[] = [];

    memberListPageIndex = 0;
    memberListPageSize = 25;
    length: number;

    constructor(
        private jwtService: JwtService,
        private userService: UserService) {
        // this.createFakeData();
        this.userService.getOwnGroup().subscribe((res: Group) => {
            this.group = res;
            this.doFilter();
            console.log(res);
        });
    }

    ngOnInit() {
        this.userService.getUserGroups().subscribe(
            (res: any) => {
                if(res.internalCode === 0) {
                    this.groups = res.content;
                }
            });
    }

    onMemberListPageChange(event: PageEvent) {
        this.userService.loadMemberList(this.group.id, event.pageIndex, event.pageSize).subscribe(
            (res: any) => {
                if(res.internalCode === 0) {
                    this.memberListPageIndex = res.content.pageIndex;
                    this.memberListPageSize = event.pageSize;
                    this.length = res.content.totalElements;

                    this.allMembers = res.content.content;
                    this.filteredUsers = res.content.content;
                }
            });
    }

    doFilter() {
        const pageEvent = new PageEvent();
        pageEvent.pageSize = 25;
        pageEvent.pageIndex = 0;
        this.onMemberListPageChange(pageEvent);
    }
}

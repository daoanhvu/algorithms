import { Component, OnInit } from '@angular/core';

import { User, Group } from '@app/models';
import { UserService } from '@app/services/user.service';
import { JwtService } from '@app/services/jwt.service';

@Component({
    selector: 'app-group',
    styleUrls: ['./group.component.scss'],
    templateUrl: './group.component.html'
})
export class GroupComponent implements OnInit {
    group: Group;
    allMembers: User[] = [];
    filteredUsers: User[] = [];

    constructor(
        private jwtService: JwtService,
        private userService: UserService) {

    }

    ngOnInit() {
        this.userService.loadMemberList(this.jwtService.getCredentials().userid).subscribe(
            (res: any) => {

        });
    }
}

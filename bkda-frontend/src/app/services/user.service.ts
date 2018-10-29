import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { HttpService } from '@app/core/http';
import { JwtService } from '@app/services/jwt.service';

import { Group } from '@app/models';

@Injectable()
export class UserService {
    constructor(
        private http: HttpService,
        private jwtService: JwtService) { }

    getUserGroups(): Observable<any> {
        const userId = this.jwtService.getCredentials().userId;
        return this.http.get('/api/v1/groups/list/user/' + userId)
            .pipe( map( (res: any) => {
                return res;
            } ) );
    }
    getOwnGroup(): Observable<Group> {
        const userId = this.jwtService.getCredentials().userId;
        return this.http.get('/api/v1/groups/user/' + userId)
            .pipe( map( (res: any) => {
                return res.content;
            } ) );
    }

    loadMemberList(groupId: number, page: number, size: number): Observable<any> {
        return this.http.get('/api/v1/groups/members/' + groupId + '?page=' + page + '&size=' + size)
            .pipe( map( (res: any) => {
                return res;
            } ) );
    }
}

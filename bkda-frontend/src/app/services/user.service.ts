import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { HttpService } from '@app/core/http';
import { JwtService } from '@app/services/jwt.service';

@Injectable()
export class UserService {
    constructor(
        private http: HttpService,
        private jwtService: JwtService) { }

    loadMemberList(userId: number, page: number, size: number): Observable<any> {
        const params = {
            groupId: this.jwtService.getCredentials().groupid,
            userId: userId
        };
        return this.http.post('/api/v1/groups/members?page=' + page + '&pageSize=' + size, params)
            .pipe( map( (res: any) => {
                return res;
            } ) );
    }
}

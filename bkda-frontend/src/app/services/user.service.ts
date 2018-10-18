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

    loadMemberList(userId: number): Observable<any> {
        return this.http.get('/api/v1/users/groups/' + userId)
            .pipe( map( (res: any) => {
                return res;
            } ) );
    }
}

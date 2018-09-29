import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { HttpService } from '@app/core/http/http.service';
import { environment } from '@env/environment';
import { Credential } from '@app/models';
import { JwtService } from './jwt.service';
import { LoginInfo } from '@app/models/login.model';

@Injectable()
export class AuthenticationService {
  private _credentials: Credential | null;

  constructor(
    private http: HttpService
  ) { }

  login(loginObj: LoginInfo): Observable<any> {
    const params = {

    };

    return this.http.post('api/v1/users/signin', params).pipe(
      map( (res: any) => {
        if( res && res.body ) {
          const accessToken: string = res.body.accessToken;
          const data = {
            username: loginObj.username.toLowerCase(),
            token: accessToken
          };
          this.setCredential(data, loginObj.remember);
          return res;
        }
      } ) );
  }
  
  isAuthenticated(): boolean {
    return !!this.credential;
  }

  get credential(): Credential | null {
    return this._credentials;
  }

  private setCredential(credential?: Credential, remember?: boolean) {
    this._credentials = credential || null;
    if (credential) {
      const storage = remember ? localStorage : sessionStorage;
      // this.jwtService.saveCredentials(credentials, storage);
    } else {
      // this.jwtService.destroyCredentials();
    }
  }
}

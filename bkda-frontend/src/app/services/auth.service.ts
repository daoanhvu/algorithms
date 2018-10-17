import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { HttpService } from '@app/core/http';
import { Credentials } from '@app/models';
import { JwtService } from './jwt.service';
import { LoginInfo, User } from '@app/models';

@Injectable()
export class AuthenticationService {
  private _credentials: Credentials | null;

  constructor(
    private http: HttpService,
    private jwtService: JwtService
  ) { }

  login(loginObj: LoginInfo): Observable<any> {
    // TODO: implement here
    const params = {
      'username': loginObj.username,
      'password': loginObj.password,
      'grantType': 'password'
    };

    return this.http.post('/api/v1/users/signin', params)
    .pipe( map( (res: any) => {
        if (res && res.internalCode === 0) {
          const accessToken: string = res.content.accessToken;
          const data: Credentials = {
            username: loginObj.username.toLowerCase(),
            token: accessToken
          };
          this.setCredentials(data, loginObj.remember);
          return res;
        }
      }));
  }

  /**
   * Logs out the user and clear credentials.
   * @return True if the user was logged out successfully.
   */
  logout(): Observable<boolean> {
    // Customize credentials invalidation here
    this.setCredentials();
    return of(true);
  }

  register(newUser: any): Observable<any> {
    return this.http
      .post('/api/v1/users/signup', newUser)
      .pipe( map( (res: any) => {
        if ( res && res.statusCode === 201 ) {
          return res.body;
        }
      }));
  }

  isAuthenticated(): boolean {
    return this.jwtService.getCredentials() ? true : false;
  }

  get credentials(): Credentials | null {
    return this._credentials;
  }

  private setCredentials(credentials?: Credentials, remember?: boolean) {
    this._credentials = credentials || null;
    if (credentials) {
      const storage = remember ? localStorage : sessionStorage;
      this.jwtService.saveCredentials(credentials, storage);
    } else {
      this.jwtService.destroyCredentials();
    }
  }
}

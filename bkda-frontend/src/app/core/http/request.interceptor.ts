import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpSentEvent,
  HttpHeaderResponse,
  HttpProgressEvent,
  HttpResponse,
  HttpUserEvent,
  HttpErrorResponse,
  HttpHeaders,
  HttpClient
} from '@angular/common/http';
import { MatSnackBar } from '@angular/material';
import { BehaviorSubject, Observable, throwError, of, EMPTY } from 'rxjs/index';
import { catchError } from 'rxjs/internal/operators/catchError';
import { filter } from 'rxjs/internal/operators/filter';
import { take } from 'rxjs/internal/operators/take';
import { switchMap } from 'rxjs/internal/operators/switchMap';
import { finalize } from 'rxjs/internal/operators/finalize';
import { Router } from '@angular/router';

import { environment } from '@env/environment';
import { JwtService } from '@app/services/jwt.service';
import { BASE_URL } from '../base-url';

const credentialsKey = 'credentials';

@Injectable()
export class RequestInterceptor implements HttpInterceptor {
  isRefreshingToken: Boolean = false;
  tokenSubject: BehaviorSubject<string> = new BehaviorSubject<string>(null);

  constructor(private jwtService: JwtService, private router: Router, public snackBar: MatSnackBar) {}

  updateHostURL(request: HttpRequest<any>): HttpRequest<any> {
    const fullURL = request.url.startsWith('http')
      ? request.url
      : environment.apiUrl + request.url;
    const credentials = this.jwtService.getCredentials();
    return request.clone({ url: fullURL });
  }

  addToken(req: HttpRequest<any>, token: string): HttpRequest<any> {
    return req.clone({ setHeaders: { Authorization: 'Bearer ' + token } });
  }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpSentEvent | HttpHeaderResponse | HttpProgressEvent | HttpResponse<any> | HttpUserEvent<any> | any> {
    if (this.jwtService.getCredentials()) {
      req = this.addToken(req, this.jwtService.getCredentials().token);
    }
    return next.handle(this.updateHostURL(req)).pipe(
      catchError(error => {
        if (error instanceof HttpErrorResponse) {
          switch ((<HttpErrorResponse>error).status) {
            case 400:
              return throwError('Error 400');
            case 401:
              return this.handle401Error(req, next);
          }
        } else {
          return throwError(error);
        }
      })
    );
  }

  handle401Error(req: HttpRequest<any>, next: HttpHandler) {
    const serverUrl = environment.apiUrl;
    const refreshTokenUrl = serverUrl + BASE_URL.signinUrl;
    const savedCredentials = JSON.parse(
      localStorage.getItem(credentialsKey)
    );
    if (!savedCredentials) {
      this.snackBar.open('Your session is expired! Please login again.', 'X');
      this.logout();
      return of(null);
    }

    if (!this.isRefreshingToken) {
      this.isRefreshingToken = true;

      // Reset here so that the following requests wait until the token
      // comes back from the refreshToken call.
      this.tokenSubject.next(null);

      const headersConfig = new HttpHeaders({
        'Content-Type': 'application/json',
        'ApiKey': environment.clientId,
        'scope': environment.scope
      });
      const refreshTokenBody = {
        // grantType: environment.grantTypeRefreshToken,
        clientId: environment.clientId,
        scope: environment.scope,
        refreshToken: savedCredentials.refreshToken
      };
      const loadTenantBody = {
        email: savedCredentials.username,
        page: 1,
        size: 1
      };

      const refreshTokenRequest = new HttpRequest('POST', refreshTokenUrl, JSON.stringify(refreshTokenBody), {
        headers: headersConfig
      });

      return new HttpClient(next).request(refreshTokenRequest).pipe(
        switchMap((newTokenResponse: any) => {
          if (newTokenResponse instanceof HttpResponse) {
            const newAccessToken = newTokenResponse.body.body.accessToken;
            const newCredentials = this.jwtService.getCredentials();
            newCredentials.token = newAccessToken;
            this.jwtService.updateCredentials(newCredentials);
            this.tokenSubject.next(newAccessToken);

            const tenantHeadersConfig = new HttpHeaders({
              'Content-Type': 'application/json',
              'ApiKey': environment.clientId,
              'scope': environment.scope,
              'Authorization': `Bearer ${newAccessToken}`
            });
          }
          return of(null);
        }),
        catchError(error => {
          this.logout();
          return of(null);
        }),
        finalize(() => {
          this.isRefreshingToken = false;
        })
      );
    } else {
      this.isRefreshingToken = false;
      return this.tokenSubject.pipe(
        filter(token => token != null),
        take(1),
        switchMap(token => {
          return next.handle(this.updateHostURL(this.addToken(req, token)));
        })
      );
    }
  }

  logout() {
    this.jwtService.destroyCredentials();
    this.router.navigate(['/login'], { replaceUrl: true });
  }
}

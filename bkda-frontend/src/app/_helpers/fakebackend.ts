import { Injectable } from '@angular/core';
import { HttpRequest, HttpResponse, HttpHandler, HttpEvent, HttpInterceptor, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { delay, mergeMap, materialize, dematerialize } from 'rxjs/operators';

@Injectable()
export class FakeBackendInterceptor implements HttpInterceptor {

	constructor() {}

	intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
		let users: any[] = JSON.parse( localStorage.getItem('users') ) || [];

		return of(null).pipe(mergeMap( () => {
			// endpoint to authentication
			if( request.url.endsWith('users/authenticate') && request.method === 'POST' ) {
				let filterUsers = users.filter(u => {
					return u.username === request.body.username && u.password === request.body.password;
				} );

				if( filterUsers.length ) {
					let user = filterUsers[0];
					let body = {
						id: user.id,
						username: user.useranme,
						lastname: user.lastname,
						firtname: user.firstname,
						token: "fake-jwt-token"
					};

					return of( new HttpResponse({ status: 200, body: body }) );
				} else {
					// retur status 400, bad request
					return throwError({ error: { message: 'authentication failed' } });	
				}


			} 
			// endpoint to get list users
			else if( request.url.endsWith('/api/v1/users') && request.method === 'GET' ) {
				
				let users = [
					{
						id: '1',
						firstname: 'Vo',
						lastname: 'Tuan Anh',
						username: 'anhvt@vinpt.com.vn',
					},
					{
						id: '2',
						firstname: 'Dao',
						lastname: 'Vu',
						username: 'vuda@vinpt.com.vn',
					}
				];

				let body = {
					internalCode: 0,
					message: null,
					content: users
				};

				return of( new HttpResponse( {status: 200, body: body} ) );
			}

			return next.handle(request);	
		} ))

		.pipe(materialize())
		.pipe(delay(300))
		.pipe(dematerialize());
		
	}
}

export let FakeBackendProvider = {
	//
	provide: HTTP_INTERCEPTORS,
	useClass: FakeBackendInterceptor,
	multi: true
};
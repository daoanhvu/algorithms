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
			if( request.url.endsWith('api/v1/users/authenticate') && request.method === 'POST' ) {
				let filterUsers = users.filter(u => {
					return u.username === request.body.username && u.password === request.body.password;
				} );

				if( filterUsers.length ) {
					let user = filterUsers[0];
					let body = {
						id: user.id,
						username: user.username,
						lastname: user.lastname,
						firstname: user.firstname,
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
				
				// let users = [
				// 	{
				// 		id: '1',
				// 		firstname: 'Vo',
				// 		lastname: 'Tuan Anh',
				// 		username: 'anhvt@vinpt.com.vn',
				// 	},
				// 	{
				// 		id: '2',
				// 		firstname: 'Dao',
				// 		lastname: 'Vu',
				// 		username: 'vuda@vinpt.com.vn',
				// 	}
				// ];

				let body = {
					internalCode: 0,
					message: '',
					content: users
				};

				return of( new HttpResponse( {status: 200, body: body} ) );
			}

      // endpoint for register
      else if( request.url.endsWith("api/v1/users/register") && request.method === 'POST' ) {
        // get new user object from post body
        let newUser = request.body;
        // validation
        let duplicateUser = users.filter(user => { return user.username === newUser.username; }).length;
        if (duplicateUser) {
            return throwError({ error: { message: 'Username "' + newUser.username + '" is already taken' } });
        }
        // save new user
        newUser.id = users.length + 1;
        users.push(newUser);
        localStorage.setItem('users', JSON.stringify(users));
        // respond 200 OK
        return of(new HttpResponse({ status: 200 }));
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
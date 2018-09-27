import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

import { environment } from '../../environments/environment';

@Injectable() 
export class AuthenticationService {

	constructor( private http: HttpClient ) { }

	login(username: string, password: string) {
		return this.http.post<any>(`${environment.apiUrl}/api/v1/users/authenticate`, { username: username, password: password })
		.pipe(map(u => {
			if( u && u.token ) {
				localStorage.setItem('currentUser', JSON.stringify(u));
			}
		}));
	}

	logout() {
		localStorage.removeItem("currentUser");
	}
}
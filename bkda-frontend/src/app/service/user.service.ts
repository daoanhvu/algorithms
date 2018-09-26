import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../_models/user';

@Injectable()
export class UserService {

	constructor(private http: HttpClient) {}

	search() {
		return this.http.get<User[]>(`${config.apiUrl}/users`);
	}
}
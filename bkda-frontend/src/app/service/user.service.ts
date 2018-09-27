import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../_models/user';
import { ResponseDTO } from '../_models/responsedto';

import { environment } from '../../environments/environment';

@Injectable()
export class UserService {

	constructor(private http: HttpClient) {}

  register(user: User) {
      return this.http.post(`${environment.apiUrl}/api/v1/users/register`, user);
  }

	search() {
		return this.http.get<ResponseDTO>(`${environment.apiUrl}/api/v1/users`); 
	}
}
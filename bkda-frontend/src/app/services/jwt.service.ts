import { Injectable } from '@angular/core';

import { Credentials } from '@app/models';

const credentialsKey = 'credentials';

@Injectable()
export class JwtService {
  getCredentials(): Credentials {
    return JSON.parse(sessionStorage.getItem(credentialsKey) || localStorage.getItem(credentialsKey));
  }

  saveCredentials(credentials: Credentials, type: any) {
    type.setItem(credentialsKey, JSON.stringify(credentials));
  }

  destroyCredentials() {
    sessionStorage.removeItem(credentialsKey);
    localStorage.removeItem(credentialsKey);
  }

  updateCredentials(credentials: Credentials) {
    sessionStorage.getItem(credentialsKey)
      ? sessionStorage.setItem(credentialsKey, JSON.stringify(credentials))
      : localStorage.setItem(credentialsKey, JSON.stringify(credentials));
  }
}

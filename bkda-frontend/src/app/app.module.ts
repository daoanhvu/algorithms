import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { routing } from './app.routing';

import { AlertComponent } from './_directives/alert.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

// This is used to create fake backend
import { FakeBackendInterceptor } from './_helpers/fakebackend';

import { AuthGuard } from './_guards/auth.guard';
import { JwtInterceptor } from './_helpers/jwt.interceptor';
import { ErrorInterceptor } from './_helpers/error.interceptor';
import { AlertService } from './service/alert.service';
import { AuthenticationService } from './service/authService';
import { UserService } from './service/user.service';

@NgModule({
  declarations: [
    AppComponent,
    AlertComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    routing
  ],
  providers: [
    AuthGuard,
    AlertService,
    AuthenticationService,
    UserService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: FakeBackendInterceptor, multi: true },
    // FakeBackendProvider
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

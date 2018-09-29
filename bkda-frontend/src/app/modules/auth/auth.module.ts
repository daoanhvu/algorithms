import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from '@app/modules/auth/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';
import { MaterialModule } from '@app/material.module';
import { AuthenticationRoutingModule } from '@app/modules/auth/auth.routing.module';
import { RegisterComponent } from '@app/modules/auth/register/register.component';

@NgModule({
    imports: [ 
        CommonModule,
        ReactiveFormsModule,
        TranslateModule,
        MaterialModule,
        AuthenticationRoutingModule
    ],
    declarations: [ LoginComponent, RegisterComponent ]
})

export class AuthenticationModule { }
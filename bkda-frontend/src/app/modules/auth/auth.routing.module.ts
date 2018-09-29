import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { LoginComponent } from "@app/modules/auth/login/login.component";
import { RegisterComponent } from "@app/modules/auth/register/register.component";

const routes: Routes = [
    {
        path: 'login',
        component: LoginComponent,
        data: {
            title: 'Login'
        }
    },
    {
        path: 'register',
        component: RegisterComponent,
        data: {
            title: 'Sign Up'
        }

    }
];

@NgModule({
    imports: [ RouterModule.forChild( routes ) ],
    exports: [ RouterModule ],
    providers: []
})
export class AuthenticationRoutingModule { }
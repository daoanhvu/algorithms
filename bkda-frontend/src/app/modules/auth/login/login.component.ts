import { Component, OnInit } from "@angular/core";
import { FormGroup, Validators, FormBuilder } from "@angular/forms";
import { Router } from "@angular/router";
import { AuthenticationService } from "@app/services/auth.service";
import { HttpService } from '@app/core/http/http.service';

@Component({
    selector: 'page-login',
    styleUrls: ['./login.component.scss'],
    templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
    formGroup: FormGroup;
    iLoading = false;
    error: boolean;
    username: string;
    password: string;

    constructor(
        private router: Router,
        private formBuilder: FormBuilder,
        private authenService: AuthenticationService,
        private http: HttpService,
        ) {
        this.formGroup = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });
     }

    ngOnInit() {
        if( this.authenService.isAuthenticated() ) {
            
        }
    }

    login() {

    }
}
import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { AuthenticationService } from '@app/services/auth.service';

@Component({
    selector: 'page-login',
    styleUrls: ['./login.component.scss'],
    templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
    formGroup: FormGroup;
    isLoading = false;
    error: string;
    username: string;
    password: string;
    hide = true;

    constructor(
        private router: Router,
        private formBuilder: FormBuilder,
        private authenService: AuthenticationService
        ) {
        this.formGroup = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required],
            remember: true
        });
     }

    ngOnInit() {
        if ( this.authenService.isAuthenticated() ) {
            this.router.navigate(['/'], { replaceUrl: true });
        }
        this.onChanges();
    }

    onChanges() {
        this.formGroup.valueChanges.subscribe( () => {
            this.error = null;
        });
    }

    login() {
        if ( this.formGroup.invalid ) {
            return;
        }

        this.isLoading = true;
        this.authenService.login(this.formGroup.value)
            .pipe( finalize( () => {
                this.formGroup.markAsPristine();
                this.isLoading = false;
            }))
            .subscribe( res => {
                console.log(res);
                if (res.internalCode === 0) {
                    this.router.navigate(['/home'], { replaceUrl: true });
                } else {
                    this.onLoginFailed('noPermission');
                }
            }, error => {
                if ( error === 400 ) {
                    this.onLoginFailed('noPermission');
                } else {
                    this.onLoginFailed('signinFailed');
                }
            });
    }

    private onLoginFailed(type: string) {
        this.error = type;
    }
}

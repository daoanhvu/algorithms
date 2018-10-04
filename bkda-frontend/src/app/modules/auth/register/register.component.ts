import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '@app/services/auth.service';

@Component({
    selector: 'page-register',
    styleUrls: ['./register.component.scss'],
    templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit {
    formGroup: FormGroup;
    isLoading = false;

    constructor(
        private router: Router,
        private formBuilder: FormBuilder,
        private authenService: AuthenticationService
    ) {
        this.formGroup = this.formBuilder.group(
            {
                firstname: ['', Validators.required],
                lastname: ['', Validators.required],
                username: ['', Validators.required],
                password: ['', Validators.required]
            }
        );
    }

    ngOnInit() {
        if (this.authenService.isAuthenticated()) {
            this.router.navigate(['/'], { replaceUrl: true  });
        }
        this.onChanges();
    }

    private onChanges() {

    }

    register() {
        if (this.formGroup.invalid) {
            return;
        }

        const params = {
            firstName: this.formGroup.value.firstname,
            lastName: this.formGroup.value.lastname,
            username: this.formGroup.value.username,
            password: this.formGroup.value.password
        };

        this.authenService.register(params)
        .subscribe( (res: any) => {
            console.log(res);
        });
    }
}

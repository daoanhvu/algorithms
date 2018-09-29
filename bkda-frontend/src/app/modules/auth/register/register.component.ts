import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";

@Component({
    selector: 'page-register',
    styleUrls: ['./register.component.scss'],
    templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit {
    formGroup: FormGroup;
    isLoading = false;

    constructor(
        private formBuilder: FormBuilder
    ) { 
        this.formGroup = this.formBuilder.group(
            { firstname: ['', Validators.required] }
        );
    }

    ngOnInit() {

    }
}
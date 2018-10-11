import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MaterialModule } from '@app/material.module';
import { ShellComponent } from './shell.component';
import { SharedModule } from '@app/modules/shared';
import { HeaderComponent } from '@app/modules/shell/header/header.component';

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        TranslateModule,
        SharedModule,
        FlexLayoutModule,
        MaterialModule,
        RouterModule
     ],
    declarations: [ ShellComponent, HeaderComponent ]
})

export class ShellModule { }

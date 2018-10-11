import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MaterialModule } from '@app/material.module';
// import {
//     ForbiddenNameDirective
//   } from './directives';
//   import {
//     LoaderComponent,
//     HeadingComponent,
//     AlertComponent
//   } from './components';

@NgModule({
    imports: [ 
        FlexLayoutModule,
        CommonModule,
        MaterialModule
     ],
    entryComponents: [],
    declarations: [  ],
    exports: []
})

export class SharedModule { }
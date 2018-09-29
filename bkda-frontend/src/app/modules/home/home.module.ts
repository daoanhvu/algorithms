import { NgModule } from "@angular/core";
import { HomeRoutingModule } from "@app/modules/home/home.routing.module";
import { HomeComponent } from "@app/modules/home/home.component";
import { CommonModule } from "@angular/common";
import { TranslateModule } from "@ngx-translate/core";
import { ReactiveFormsModule, FormsModule } from "@angular/forms";
import { CoreModule, FlexLayoutModule } from "@angular/flex-layout";
import { SharedModule } from "@app/modules/shared";
import { MaterialModule } from "@app/material.module";

@NgModule({
    imports: [ 
        CommonModule,
        TranslateModule,
        ReactiveFormsModule,
        CoreModule,
        SharedModule,
        FlexLayoutModule,
        MaterialModule,
        HomeRoutingModule,
        FormsModule
    ],
    entryComponents: [ HomeComponent ],
    declarations: [ 
        HomeComponent
    ]
})
export class HomeModule { }
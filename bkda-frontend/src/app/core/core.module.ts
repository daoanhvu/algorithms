import { NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouteReuseStrategy, RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';
import { FlexLayoutModule } from '@angular/flex-layout';

import { MaterialModule } from '@app/material.module';
import { HttpService } from '@app/core/http/http.service';
import { AuthenticationGuard } from '@app/core/authentication/authentication.guard';

@NgModule({
    imports: [
        CommonModule,
        HttpClientModule,
        TranslateModule,
        FlexLayoutModule,
        MaterialModule,
        RouterModule
    ],
    declarations: [],
    providers: [
        HttpService,
        AuthenticationGuard
    ]
})
export class CoreModule {
    constructor(
        @Optional()
        @SkipSelf()
        parentModule: CoreModule
    ) { 
        //import guard
        if( parentModule ) {
            throw new Error(`${parentModule} has already been loaded. Import Core module in the AppModule only.`);
        }
    }
 }
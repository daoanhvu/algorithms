import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { ServiceWorkerModule } from '@angular/service-worker';
import { TranslateModule } from '@ngx-translate/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { SatDatepickerModule, SatNativeDateModule } from 'saturn-datepicker';
import { MAT_SNACK_BAR_DEFAULT_OPTIONS } from '@angular/material';

import { ShellModule } from '@app/modules/shell/shell.module';
import { ErrorHandlingModule } from '@app/modules/errors/errorhandling.module';
import { AppRoutingModule } from '@app/app.routing.module';

import { AppComponent } from './app.component';
import { SharedModule } from '@app/modules/shared';
import { AuthenticationModule } from '@app/modules/auth/auth.module';
import { AuthenticationService } from '@app/services/auth.service';
import { CoreModule } from '@app/core/core.module';
import { HomeModule } from '@app/modules/home/home.module';
import { ProductModule } from '@app/modules/products/product.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    HttpModule,
    BrowserAnimationsModule,
    MaterialModule,
    CoreModule,
    SharedModule,
    AuthenticationModule,
    ShellModule,
    ErrorHandlingModule,
    HomeModule,
    ProductModule,
    AppRoutingModule
  ],
  providers: [
    {provide: MAT_SNACK_BAR_DEFAULT_OPTIONS, useValue: {duration: 2000, verticalPosition: 'top', horizontalPosition: 'end'}},
    AuthenticationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

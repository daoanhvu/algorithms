import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { ServiceWorkerModule } from '@angular/service-worker';
// import { TranslateModule } from '@ngx-translate/core';
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
import { CoreModule } from '@app/core/core.module';
import { HomeModule } from '@app/modules/home/home.module';
import { ProductModule } from '@app/modules/products/product.module';
import { RequestInterceptor } from '@app/core/http';

@NgModule({
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
  declarations: [AppComponent],
  providers: [
    {provide: MAT_SNACK_BAR_DEFAULT_OPTIONS, useValue: {duration: 3500, verticalPosition: 'top', horizontalPosition: 'end'}},
    {
      provide: HTTP_INTERCEPTORS,
      useClass: RequestInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouteReuseStrategy, RouterModule } from '@angular/router';
// import { TranslateModule } from '@ngx-translate/core';
import { FlexLayoutModule } from '@angular/flex-layout';

import { MaterialModule } from '@app/material.module';
import { RouteReusableStrategy } from './route-reusable-strategy';
// import { I18nService } from './i18n.service';
import { AuthenticationGuard } from './authentication/authentication.guard';
import { HttpService, HttpCacheService, RequestInterceptor,
    CacheInterceptor } from './http';
import { AuthenticationService } from '@app/services/auth.service';
import { JwtService } from '@app/services/jwt.service';

@NgModule({
  imports: [CommonModule, HttpClientModule,
    // TranslateModule,
    FlexLayoutModule, MaterialModule, RouterModule],
  declarations: [],
  providers: [
    // I18nService,
    HttpCacheService,
    RequestInterceptor,
    AuthenticationGuard,
    CacheInterceptor,
    HttpService,
    {
      provide: RouteReuseStrategy,
      useClass: RouteReusableStrategy
    },
    JwtService,
    AuthenticationService
  ]
})
export class CoreModule {
  constructor(
    @Optional()
    @SkipSelf()
    parentModule: CoreModule
  ) {
    // Import guard
    if (parentModule) {
      throw new Error(`${parentModule} has already been loaded. Import Core module in the AppModule only.`);
    }
  }
}

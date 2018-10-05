import { NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouteReuseStrategy, RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';
import { FlexLayoutModule } from '@angular/flex-layout';

import { MaterialModule } from '@app/material.module';
import { RouteReusableStrategy } from './route-reusable-strategy';
import { CanActivateGuard } from './can-activate.guard';
// import { I18nService } from './i18n.service';
import { HttpService, HttpCacheService, ApiPrefixInterceptor,
    ErrorHandlerInterceptor, CacheInterceptor } from './http';
import { AuthenticationService, JwtService, TenantService,
    EnvConfigService, QueryDslService, PermissionService} from './services';

@NgModule({
  imports: [CommonModule, HttpClientModule, TranslateModule, FlexLayoutModule, MaterialModule, RouterModule],
  declarations: [],
  providers: [
    CanActivateGuard,
    // I18nService,
    HttpCacheService,
    ApiPrefixInterceptor,
    ErrorHandlerInterceptor,
    CacheInterceptor,
    HttpService,
    {
      provide: RouteReuseStrategy,
      useClass: RouteReusableStrategy
    },
    JwtService,
    AuthenticationService,
    TenantService,
    EnvConfigService,
    QueryDslService,
    PermissionService
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

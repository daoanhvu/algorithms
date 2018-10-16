import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';
import { NotFoundComponent } from '@app/modules/errors/notfound/notfound.component';
// import { ShellComponent } from '@app/modules/shell/shell.component';
// import { LoginComponent } from '@app/modules/auth/login/login.component';

const routes: Routes = [
  // { path: '', component: ShellComponent },
  // { path: 'login', component: LoginComponent },
  // Fallback when no prior route is matched
  { path: '**', component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule {}

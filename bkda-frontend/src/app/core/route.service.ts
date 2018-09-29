import { Routes, Route } from "@angular/router";
import { ShellComponent } from "@app/modules/shell/shell.component";
import { AuthenticationGuard } from "@app/core/authentication/authentication.guard";

export class BKDARoute {
    static withShell(routes: Routes): Route {
        return {
            path: '',
            component: ShellComponent,
            children: routes,
            canActivate: [ AuthenticationGuard ],
            // Reuse ShellComponent instance when navigating between child views
            data: { reuse: true }
        };
    }
}
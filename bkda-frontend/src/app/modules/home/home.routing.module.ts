import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home.component';
import { BKDARoute } from '@app/core/route.service';

const routes: Routes = [
    BKDARoute.withShell([
        {
            path: '',
            component: HomeComponent
        },
        {
            path: '/home',
            component: HomeComponent
        }
    ])
];

@NgModule({
    imports: [ RouterModule.forChild( routes ) ],
    exports: [ RouterModule ],
    providers: []
})
export class HomeRoutingModule { }

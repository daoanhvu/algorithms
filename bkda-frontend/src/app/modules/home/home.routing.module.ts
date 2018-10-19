import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home.component';
import { BKDARoute } from '@app/core/route.service';
import { GroupComponent } from '@app/modules/group/group.component';

const routes: Routes = [
    BKDARoute.withShell([
        {
            path: '',
            redirectTo: '/home',
            pathMatch: 'full'
        },
        {
            path: 'home',
            children: [
                {
                    path: '',
                    component: HomeComponent,
                    data: {}
                },
                {
                    path: 'group',
                    component: GroupComponent,
                    data: { }
                }
            ]
        }
    ])
];

@NgModule({
    imports: [ RouterModule.forChild( routes ) ],
    exports: [ RouterModule ],
    providers: []
})
export class HomeRoutingModule { }

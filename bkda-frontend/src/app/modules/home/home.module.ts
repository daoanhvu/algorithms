import { NgModule } from '@angular/core';
import { HomeRoutingModule } from '@app/modules/home/home.routing.module';
import { HomeComponent } from '@app/modules/home/home.component';
import { CommonModule } from '@angular/common';
// import { TranslateModule } from '@ngx-translate/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CoreModule } from '@app/core/core.module';
import { SharedModule } from '@app/modules/shared';
import { MaterialModule } from '@app/material.module';
import { ProductListComponent } from '@app/modules/products/productlist/productlist.component';
import { ProductService } from '@app/services/product.service';
import { ProductCardComponent } from '@app/modules/products/productcard/productcard.component';
import { GroupListComponent } from '@app/modules/group/grouplist/grouplist.component';
import { LoggedUserComponent } from '@app/modules/home/loggeduser/loggeduser.component';
import { GroupComponent } from '@app/modules/group/group.component';
import { UserDetailComponent } from '@app/modules/user/user.details/userdetail.component';
import { UserService } from '@app/services/user.service';

@NgModule({
    imports: [
        CommonModule,
        // TranslateModule,
        ReactiveFormsModule,
        CoreModule,
        SharedModule,
        FlexLayoutModule,
        MaterialModule,
        FormsModule,
        HomeRoutingModule,
        // ProductModule
    ],
    entryComponents: [ HomeComponent ],
    declarations: [
        HomeComponent, LoggedUserComponent, GroupComponent,
        GroupListComponent, ProductListComponent, ProductCardComponent,
        UserDetailComponent
    ],
    providers: [
        ProductService, UserService
    ]
})
export class HomeModule { }

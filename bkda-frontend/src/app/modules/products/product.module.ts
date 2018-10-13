import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatGridListModule } from '@angular/material';

import { MaterialModule } from '@app/material.module';
import { ProductListComponent } from '@app/modules/products/productlist/productlist.component';

@NgModule({
    imports: [
        CommonModule,
        MaterialModule,
        MatGridListModule
    ],
    declarations: [ ProductListComponent ]
})
export class ProductModule { }

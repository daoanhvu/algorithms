import { Component, OnInit, Input } from '@angular/core';
import { map } from 'rxjs/operators';
import { Product } from '@app/models/product.model';

@Component({
    selector: 'product-list',
    styleUrls: ['./productlist.component.scss'],
    templateUrl: './productlist.component.html'
})
export class ProductListComponent implements OnInit {
    @Input()
    dataSource: Product[] = [];

    constructor() {
    }

    ngOnInit() {
    }
}

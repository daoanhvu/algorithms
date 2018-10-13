import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Product } from '@app/models/product.model';
import { ProductService } from '@app/services/product.service';

@Component({
    selector: 'product-list',
    styleUrls: ['./productlist.component.scss'],
    templateUrl: './productlist.component.html'
})
export class ProductListComponent implements OnInit {
    tiles: Product[] = [];

    constructor(private productService: ProductService) {

    }

    ngOnInit() {
        this.productService.loadProductList().subscribe(
            (res: any) => {
                if (res.statusCode === 200) {
                    this.tiles = res.body.content;
                }
            }
        );
    }
}

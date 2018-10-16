import { Component, OnInit } from '@angular/core';
import { ProductService } from '@app/services/product.service';

@Component({
    selector: 'app-product-card',
    styleUrls: ['./productcard.component.scss'],
    templateUrl: './productcard.component.html'
})
export class ProductCardComponent implements OnInit {

    productId: number;

    constructor(private productService: ProductService) {
    }

    ngOnInit() {
    }

}

import { Component, OnInit, Input } from '@angular/core';
import { ProductService } from '@app/services/product.service';

@Component({
    selector: 'app-product-card',
    styleUrls: ['./productcard.component.scss'],
    templateUrl: './productcard.component.html'
})
export class ProductCardComponent implements OnInit {

    @Input()
    productId: number;
    productImage: any;
    isLoading = false;

    constructor(private productService: ProductService) {
    }

    ngOnInit() {
        this.isLoading = true;
        this.productService
            .getProductImage(this.productId)
            .subscribe( imageData => {
                this.createImageFromBlob(imageData);
                this.isLoading = false;
            }, error => {
                this.isLoading = false;
            } );
    }

    private createImageFromBlob(data: Blob) {
        const reader = new FileReader();
        reader.addEventListener('load', () => {
            this.productImage = reader.result;
        }, false);
        if (data) {
            reader.readAsDataURL(data);
        }
    }
}

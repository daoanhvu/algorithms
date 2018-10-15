import { Component, OnInit } from '@angular/core';
import { MatDialog, MatPaginator, PageEvent } from '@angular/material';

import { ProductService } from '@app/services/product.service';
import { Product } from '@app/models/product.model';

export class ProductFilter {
    name?: string;
    companyName?: string;
    userId?: number;
  }

@Component({
    selector: 'app-home',
    styleUrls: ['./home.component.scss'],
    templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {

    productData: Product[] = [];
    productlist_pageSize: number;
    productFilter: ProductFilter = new ProductFilter();

    constructor(private productService: ProductService) { }

    ngOnInit() {
        this.applyFilter(this.productFilter);
    }

    processPageChanged(event: PageEvent) {
        if (event.pageSize !== this.productlist_pageSize) {
          event.pageIndex = 0;
        }
        this.productService.loadProductList().subscribe(
            (res: any) => {
                if (res.statusCode === 200) {
                    this.productData = res.body.content;
                }
            }
        );
    }

    applyFilter(filterValue: ProductFilter) {
        this.productFilter = filterValue;
        const pageEvent = new PageEvent();
        pageEvent.pageSize = 25;
        pageEvent.pageIndex = 0;
        this.processPageChanged(pageEvent);
      }
}

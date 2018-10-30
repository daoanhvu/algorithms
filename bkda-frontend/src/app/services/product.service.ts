import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpService } from '@app/core/http';
import { JwtService } from '@app/services/jwt.service';
import { ResponseContentType } from '@angular/http';

@Injectable()
export class ProductService {
    constructor(
        private http: HttpService,
        private jwtService: JwtService) { }

    loadProductList(): Observable<any> {
        const params = {
            name: '',
            page: 1,
            pageSize: 25
        };
        return this.http.post('/api/v1/products/search', params)
            .pipe( map( (res: any) => {
                return res;
            } ) );
    }

    getProductById(productid: string): Observable<any> {
        return this.http.get('/api/v1/products/' + productid)
            .pipe( map( (res: any) => {
                return res;
            } ) );
    }

    getProductImage(mediaId: number): Observable<Blob> {
        return this.http.get('/api/v1/medias/' + mediaId, { responseType: 'blob' });
    }
}

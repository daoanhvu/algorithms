import { Component, OnInit, Input } from '@angular/core';
import { MatSidenav } from '@angular/material';
import { Router } from '@angular/router';
import { JwtService } from '@app/services/jwt.service';
import { Credentials } from '@app/models';
import { AuthenticationService } from '@app/services/auth.service';

@Component({
    selector: 'app-header',
    styleUrls: ['./header.component.scss'],
    templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {

    @Input()
    sidenav: MatSidenav;
    username: string;

    constructor(
        private router: Router,
        private authService: AuthenticationService,
        private jwtService: JwtService
    ) { }

    ngOnInit() {
        const credentials: Credentials = this.jwtService.getCredentials();
        this.username = credentials.username;
    }

    logout() {
        this.authService.logout().subscribe(() => this.router.navigate(['/login'], { replaceUrl: true }));
    }
}

import { Component, OnInit, ViewChild }  from '@angular/core';
import { MatSidenav } from '@angular/material';
import { ObservableMedia, MediaChange } from '@angular/flex-layout';
import { filter } from 'rxjs/operators';

@Component({
    selector: 'app-shell',
    styleUrls: ['./shell.component.scss'],
    templateUrl: './shell.component.html'
})
export class ShellComponent implements OnInit {

  @ViewChild('sidenav') sidenav: MatSidenav;

  constructor( private media: ObservableMedia ) { }

  ngOnInit() {
    this.media.asObservable()
    .pipe( filter((ch: MediaChange) => ( ch.mqAlias !== 'xs' && ch.mqAlias !== 'sm' )))
    .subscribe( () => this.sidenav.close() );
  }
 }
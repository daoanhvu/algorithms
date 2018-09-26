import { Injectable } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { Observable, Subject } from 'rxjs';

@Injectable()
export class AlertService {
	private subject = new Subject<any>();
	private keepAfterNavigationChange = false;

	constructor(private router: Router) {
		var that = this;
		//alert message on route change
		router.events.subscribe( event => {
			if( event instanceof NavigationStart ) {
				if( that.keepAfterNavigationChange ) {
					// only keep for a single location change
					that.keepAfterNavigationChange = false;
				} else {
					that.subject.next();
				}
			}
		});
	}

	success(message: string, keepAfterNavigationChange = false) {
		this.keepAfterNavigationChange = keepAfterNavigationChange;
		this.subject.next({ type: 'success', text: message });
	}

	warning(message: string, keepAfterNavigationChange = false) {
		this.keepAfterNavigationChange = keepAfterNavigationChange;
		this.subject.next({ type: 'warning', text: message });
	}

	error(message: string, keepAfterNavigationChange = false) {
		this.keepAfterNavigationChange = keepAfterNavigationChange;
		this.subject.next({ type: 'error', text: message });
	}

	getMessage(): Observable<any> {
		return this.subject.asObservable();
	}

}
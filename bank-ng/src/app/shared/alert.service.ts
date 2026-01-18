import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

export interface AlertMessage {
  type: 'success' | 'error';
  text: string;
}

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  private alertSubject = new Subject<AlertMessage>();
  alert$ = this.alertSubject.asObservable();

  success(message: string) {
    this.alertSubject.next({
      type: 'success',
      text: message
    });
  }

  error(message: string) {
    this.alertSubject.next({
      type: 'error',
      text: message
    });
  }

  clear() {
    this.alertSubject.next(null as any);
  }
}

import { Component, OnInit } from '@angular/core';
import { AlertMessage, AlertService } from '../alert.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-alert',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './alert.component.html',
  styleUrl: './alert.component.css'
})
export class AlertComponent implements OnInit {

  message: AlertMessage | null = null;

  constructor(private alertService: AlertService) {}

  ngOnInit(): void {
    this.alertService.alert$.subscribe(msg => {
      this.message = msg;

      if (msg) {
        setTimeout(() => {
          this.message = null;
        }, 4000);
      }
    });
  }

}

import { Component } from '@angular/core';
import { ReportResponse } from '../../../../models/reportResponse.model';
import { ReportService } from '../../../../core/report.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-report-view',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './report-view.component.html',
  styleUrl: './report-view.component.css',
})
export class ReportViewComponent {
  identification = '';
  startDate = '';
  endDate = '';

  report?: ReportResponse;

  constructor(private service: ReportService) {}

  search() {
    this.service
      .getReport(this.identification, this.startDate, this.endDate)
      .subscribe((res) => (this.report = res));
  }

  downloadPdf() {
    this.service
      .getPdfBase64(this.identification, this.startDate, this.endDate)
      .subscribe((base64) => {
        const link = document.createElement('a');
        link.href = 'data:application/pdf;base64,' + base64;
        link.download = 'estado-cuenta.pdf';
        link.click();
      });
  }
}

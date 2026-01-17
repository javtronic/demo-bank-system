import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environments';
import { ReportResponse } from '../models/reportResponse.model';

@Injectable({ providedIn: 'root' })
export class ReportService {

  private api = `${environment.apiUrl}/v1/reports`;

  constructor(private http: HttpClient) {}

  getReport(
    identification: string,
    startDate: string,
    endDate: string
  ): Observable<ReportResponse> {
    return this.http.get<ReportResponse>(
      `${this.api}/${identification}?startDate=${startDate}&endDate=${endDate}`
    );
  }

  getPdfBase64(
    identification: string,
    startDate: string,
    endDate: string
  ): Observable<string> {
    return this.http.get(
      `${this.api}/${identification}/pdf?startDate=${startDate}&endDate=${endDate}`,
      { responseType: 'text' }
    );
  }
}

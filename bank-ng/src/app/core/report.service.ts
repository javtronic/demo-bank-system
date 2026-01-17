import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, map, Observable, throwError } from 'rxjs';
import { environment } from '../../environments/environments';
import { ReportResponse } from '../models/reportResponse.model';
import { ApiResponse } from '../models/api-response.model';

@Injectable({ providedIn: 'root' })
export class ReportService {

  private api = `${environment.apiUrl}/v1/reports`;

  constructor(private http: HttpClient) {}

  getReport(
    identification: string,
    startDate: string,
    endDate: string
  ): Observable<ReportResponse> {
    return this.http.get<ApiResponse<ReportResponse>>(
      `${this.api}/${identification}?startDate=${startDate}&endDate=${endDate}`
    ).pipe(
          map((response) => response.data),
          catchError((error) => this.handleError(error)),
        );
  }

  getPdfBase64(
    identification: string,
    startDate: string,
    endDate: string
  ): Observable<string> {
    return this.http.get<ApiResponse<string>>(
      `${this.api}/${identification}/pdf?startDate=${startDate}&endDate=${endDate}`
    ).pipe(
      map((response) => response.data),
      catchError((error) => this.handleError(error)),
    );;
  }

      private handleError(error: any) {
        let message = 'Error inesperado';
    
        if (error?.error?.message) {
          message = error.error.message;
        }
    
        return throwError(() => message);
      }
}

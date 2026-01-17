import { Injectable } from '@angular/core';
import { environment } from '../../environments/environments';
import { HttpClient } from '@angular/common/http';
import { TransactionDetail } from '../models/transaction-detail.model';
import { TransactionRequest } from '../models/transaction.model';
import { catchError, map, throwError } from 'rxjs';
import { ApiResponse } from '../models/api-response.model';

@Injectable({ providedIn: 'root' })
export class TransactionService {
  private api = `${environment.apiUrl}/v1/transactions`;

  constructor(private http: HttpClient) {}

  findByAccountAndDate(
    accountCode: string,
    startDate: string,
    endDate: string,
  ) {
    return this.http.get<ApiResponse<TransactionDetail[]>>(
      `${this.api}/${accountCode}/movements`,
      {
        params: {
          startDate,
          endDate,
        },
      },
    ).pipe(
      map((response) => response.data),
      catchError((error) => this.handleError(error)),
    );
  }

  register(transaction: TransactionRequest) {
    return this.http.post<ApiResponse<any>>(`${this.api}/register`, transaction)
    .pipe(
          map((response) => response.message),
          catchError((error) => this.handleError(error)),
        );
  }

  private handleError(error: any) {
    let message = 'Error inesperado';

    if (error?.error?.message) {
      message = error.error.message;
    }

    return throwError(() => message);
  }
}

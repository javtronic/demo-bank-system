import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from '../models/account.model';
import { environment } from '../../environments/environments';
import { catchError, map, throwError } from 'rxjs';
import { ApiResponse } from '../models/api-response.model';

@Injectable({ providedIn: 'root' })
export class AccountService {
  private readonly baseUrl = `${environment.apiUrl}/v1/accounts`;

  constructor(private http: HttpClient) {}
  create(account: Account) {
    return this.http.post<ApiResponse<any>>(`${this.baseUrl}/create`, account)
          .pipe(
            map((response) => response.message),
            catchError((error) => this.handleError(error)),
          );
  }

  changeStatus(accountCode: string) {
    return this.http.get<ApiResponse<any>>(`${this.baseUrl}/changeStatus?accountCode=${accountCode}`)
    .pipe(
        map((response) => response.message),
        catchError((error) => this.handleError(error)),
      );
  }

  findAll() {
    return this.http.get<ApiResponse<Account[]>>(`${this.baseUrl}/findAll`).pipe(
      map((response) => response.data),
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

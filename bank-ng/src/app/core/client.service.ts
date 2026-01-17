import { Injectable } from '@angular/core';
import { environment } from '../../environments/environments';
import { HttpClient } from '@angular/common/http';
import { Client } from '../models/client.model';
import { ApiResponse } from '../models/api-response.model';
import { catchError, map, throwError } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ClientService {
  private readonly baseUrl = `${environment.apiUrl}/v1/clients`;

  constructor(private http: HttpClient) {}

  create(client: Client) {
    return this.http
      .post<ApiResponse<any>>(this.baseUrl + '/create', client)
      .pipe(
        map((response) => response.message),
        catchError((error) => this.handleError(error)),
      );
  }

  update(client: Client) {
    return this.http
      .post<ApiResponse<any>>(this.baseUrl + '/update', client)
      .pipe(
        map((response) => response.message),
        catchError((error) => this.handleError(error)),
      );
  }

  findByCode(clientCode: string) {
    return this.http.get<ApiResponse<Client>>(`${this.baseUrl}/findByCode?clientCode=${clientCode}`)
      .pipe(
        map((response) => response.data),
        catchError((error) => this.handleError(error)),
      );
  }

  changeStatus(clientCode: string) {
    return this.http.get<ApiResponse<any>>(`${this.baseUrl}/changeStatus?clientCode=${clientCode}`)
      .pipe(
        map((response) => response.message),
        catchError((error) => this.handleError(error)),
      );
  }

  getAll() {
    return this.http.get<ApiResponse<Client[]>>(this.baseUrl + '/findAll').pipe(
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

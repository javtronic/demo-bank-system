import { Injectable } from '@angular/core';
import { environment } from '../../environments/environments';
import { HttpClient } from '@angular/common/http';
import { TransactionDetail } from '../models/transaction-detail.model';
import { TransactionRequest } from '../models/transaction.model';

@Injectable({ providedIn: 'root' })
export class TransactionService {
  private api = `${environment.apiUrl}/v1/transactions`;

  constructor(private http: HttpClient) {}

  findByAccountAndDate(
    accountCode: string,
    startDate: string,
    endDate: string
  ) {
    return this.http.get<TransactionDetail[]>(
      `${this.api}/${accountCode}/movements`,
      {
        params: {
          startDate,
          endDate,
        },
      }
    );
  }

  register(transaction: TransactionRequest) {
    return this.http.post(`${this.api}/register`, transaction);
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from '../models/account.model';
import { environment } from '../../environments/environments';

@Injectable({ providedIn: 'root' })
export class AccountService {
  private readonly baseUrl = `${environment.apiUrl}/v1/accounts`;

  constructor(private http: HttpClient) {}
  create(account: Account) {
    return this.http.post(`${this.baseUrl}/create`, account);
  }

  inactivate(accountCode: string) {
    return this.http.get(`${this.baseUrl}/inactive?accountCode=${accountCode}`);
  }

  findAll() {
    return this.http.get<Account[]>(`${this.baseUrl}/findAll`);
  }

//   findByClient(clientCode: string) {
//     return this.http.get<Account[]>(`${this.baseUrl}/client/${clientCode}`);
//   }

//   findByCode(accountCode: string) {
//     return this.http.get<Account>(`${this.baseUrl}/${accountCode}`);
//   }
}

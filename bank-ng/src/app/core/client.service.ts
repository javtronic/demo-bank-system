import { Injectable } from "@angular/core";
import { environment } from "../../environments/environments";
import { HttpClient } from "@angular/common/http";
import { Client } from "../models/client.model";

@Injectable({ providedIn: 'root' })
export class ClientService {

  private readonly baseUrl = `${environment.apiUrl}/v1/clients`;

  constructor(private http: HttpClient) {}
  create(client: Client) {
    return this.http.post(this.baseUrl + '/create', client);
  }

  update(client: Client) {
    return this.http.post(this.baseUrl + '/update', client);
  }

  findByCode(clientCode: string) {
    return this.http.get<Client>(`${this.baseUrl}/findByCode?clientCode=${clientCode}`);
  }

  inactivate(clientCode: string) {
    return this.http.get(`${this.baseUrl}/inactive?clientCode=${clientCode}`);
  }

  getAll() {
    return this.http.get<Client[]>(this.baseUrl + '/findAll');
  }


}

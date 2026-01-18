import { Component, OnInit } from '@angular/core';
import { Client } from '../../../../models/client.model';
import { ClientService } from '../../../../core/client.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AlertService } from '../../../../shared/alert.service';

@Component({
  selector: 'app-client-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './client-list.component.html',
  styleUrl: './client-list.component.css',
})
export class ClientListComponent implements OnInit {
  clients: Client[] = [];
  filteredClients: Client[] = [];
  searchText: string = '';

  constructor(private clientService: ClientService, 
    private router: Router,
    private alertService: AlertService
  ) {}

  ngOnInit(): void {
    this.loadClients();
  }

  goToCreate() {
    this.router.navigate(['/clients/new']);
  }

  edit(clientCode: string) {
    this.router.navigate(['/clients/edit', clientCode]);
  }

  loadClients() {
    this.clientService.getAll().subscribe((data) => {
      this.clients = data;
       this.filteredClients = data;
    });
  }

  search() {
  const value = this.searchText.toLowerCase();

  this.filteredClients = this.clients.filter(c =>
    c.person.name.toLowerCase().includes(value) ||
    c.person.identification.toLowerCase().includes(value) ||
    c.person.phoneNumber?.toLowerCase().includes(value)
  );
  }

  changeStatus(clientCode: string) {
    this.clientService.changeStatus(clientCode).subscribe({
      next: (response) => {
        this.alertService.success(response);
         this.loadClients();
      },
      error: (message) => {
        this.alertService.error(message);
      },
    });
  }
}

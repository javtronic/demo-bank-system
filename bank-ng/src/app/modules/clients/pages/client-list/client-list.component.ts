import { Component, OnInit } from '@angular/core';
import { Client } from '../../../../models/client.model';
import { ClientService } from '../../../../core/client.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-client-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './client-list.component.html',
  styleUrl: './client-list.component.css',
})
export class ClientListComponent implements OnInit {
  clients: Client[] = [];

  constructor(private clientService: ClientService, private router: Router) {}

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
    });
  }

  inactivate(clientCode: string) {
    this.clientService.inactivate(clientCode).subscribe(() => {
      this.loadClients();
    });
  }
}

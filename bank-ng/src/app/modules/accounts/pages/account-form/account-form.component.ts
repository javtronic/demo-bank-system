import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AccountService } from '../../../../core/account.service';
import { ClientService } from '../../../../core/client.service';
import { Client } from '../../../../models/client.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-account-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './account-form.component.html',
  styleUrl: './account-form.component.css',
})
export class AccountFormComponent implements OnInit {

  form!: FormGroup
  clients: Client[] = [];

  accountTypes = ['AHORRO', 'CORRIENTE'];

  constructor(
    private fb: FormBuilder,
    private accountService: AccountService,
    private clientService: ClientService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      clientCode: ['', Validators.required],
      accountNumber: ['', Validators.required],
      accountType: ['', Validators.required],
      initialBalance: [0, [Validators.required, Validators.min(0)]],
      accountStatus: [true],
    });

    this.loadClients();
  }

  loadClients() {
    this.clientService.getAll().subscribe((res) => (this.clients = res));
  }

  save() {
    if (this.form.invalid) return;

    this.accountService.create(this.form.value).subscribe(() => {
      this.router.navigate(['/accounts']);
    });
  }
}

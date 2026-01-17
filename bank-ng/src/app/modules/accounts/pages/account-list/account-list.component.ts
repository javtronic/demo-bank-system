import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Account } from '../../../../models/account.model';
import { AccountService } from '../../../../core/account.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-account-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './account-list.component.html',
  styleUrl: './account-list.component.css'
})
export class AccountListComponent implements OnInit {

  accounts: Account[] = [];
  searchText = '';

  constructor(
    private service: AccountService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadAccounts();
  }

  createAccount() {
    this.router.navigate(['/accounts/new']);
  }

  loadAccounts() {
    this.service.findAll()
      .subscribe(res => this.accounts = res);
  }

  changeStatus(accountCode: string) {
    this.service.changeStatus(accountCode)
      .subscribe({
      next: (response) => {
        alert(response);
         this.loadAccounts();
      },
      error: (message) => {
        alert(message);
      },
    });
  }

  filteredAccounts(): Account[] {
    if (!this.searchText) return this.accounts;

    const text = this.searchText.toLowerCase();

    return this.accounts.filter(acc =>
      acc.accountNumber.includes(text) ||
      acc.client.person.name.toLowerCase().includes(text) ||
      acc.client.person.identification.includes(text)
    );
  }

}

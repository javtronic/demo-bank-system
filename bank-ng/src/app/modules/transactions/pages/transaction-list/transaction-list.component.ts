import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TransactionDetail } from '../../../../models/transaction-detail.model';
import { TransactionService } from '../../../../core/transaction.service';
import { Account } from '../../../../models/account.model';
import { AccountService } from '../../../../core/account.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AlertService } from '../../../../shared/alert.service';

@Component({
  selector: 'app-transaction-list',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './transaction-list.component.html',
  styleUrl: './transaction-list.component.css',
})
export class TransactionListComponent implements OnInit {
  accountCode = '';
  startDate = '';
  endDate = '';

  accounts: Account[] = [];
  transactions: TransactionDetail[] = [];
  selectedAccountCode?: string;

  constructor(
    private transactionService: TransactionService,
    private accountService: AccountService,
    private router: Router,
    private alertService: AlertService
  ) {}

  ngOnInit() {
    this.accountService.findAll().subscribe((data) => {
      this.accounts = data;
    });
  }

  search() {
    if (!this.accountCode || !this.startDate || !this.endDate) {
       this.alertService.error('Debe seleccionar cuenta y rango de fechas');
      return;
    }

    this.transactionService
      .findByAccountAndDate(this.accountCode, this.startDate, this.endDate)
      .subscribe((data) => {
        this.transactions = data;
      });
  }

  goToCreate() {
    this.router.navigate(['/transactions/new']);
  }
}

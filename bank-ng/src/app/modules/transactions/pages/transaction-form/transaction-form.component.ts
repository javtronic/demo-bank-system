import { Component, OnInit } from '@angular/core';
import { AccountService } from '../../../../core/account.service';
import { Account } from '../../../../models/account.model';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { TransactionService } from '../../../../core/transaction.service';
import { TransactionRequest } from '../../../../models/transaction.model';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-transaction-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './transaction-form.component.html',
  styleUrl: './transaction-form.component.css',
})
export class TransactionFormComponent implements OnInit {
  form!: FormGroup;
  accounts: Account[] = [];
  transactionTypes = ['CREDITO', 'DEBITO'];

  constructor(
    private fb: FormBuilder,
    private accountService: AccountService,
    private transactionService: TransactionService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      accountCode: ['', Validators.required],
      transactionType: ['', Validators.required],
      amount: [0, [Validators.required, Validators.min(0.01)]],
    });

    this.loadAccounts();
  }

  loadAccounts() {
    this.accountService.findAll().subscribe((res) => {
      this.accounts = res;
    });
  }

  submit() {
    if (this.form.invalid) return;

    const transaction: TransactionRequest = this.form.value;

    this.transactionService.register(transaction).subscribe({
      next: (response) => {
        alert(response);
        this.router.navigate(['/transactions']);
      },
      error: (message) => {
        alert(message);
      },
    });
  }
}

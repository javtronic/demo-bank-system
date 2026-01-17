import { Routes } from '@angular/router';
import { TransactionListComponent } from './pages/transaction-list/transaction-list.component';
import { TransactionFormComponent } from './pages/transaction-form/transaction-form.component';

export const TRANSACTIONS_ROUTES: Routes = [
  {path: '', component: TransactionListComponent},
  { path: 'new', component: TransactionFormComponent }
];

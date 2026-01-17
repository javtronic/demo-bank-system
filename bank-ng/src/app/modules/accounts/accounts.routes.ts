import { Routes } from '@angular/router';
import { AccountListComponent } from './pages/account-list/account-list.component';
import { AccountFormComponent } from './pages/account-form/account-form.component';

export const ACCOUNTS_ROUTES: Routes = [
  { path: '', component: AccountListComponent },
  { path: 'new', component: AccountFormComponent }
];
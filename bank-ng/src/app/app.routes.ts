import { Routes } from '@angular/router';

export const routes: Routes = [
    { path: 'clients', loadChildren: () => import('./modules/clients/clients.routes') },
  //  { path: 'accounts', loadChildren: () => import('./features/accounts/accounts.routes') },
  //  { path: 'transactions', loadChildren: () => import('./features/transactions/transactions.routes') },
  //  { path: 'reports', loadChildren: () => import('./features/reports/reports.routes') },
    { path: '', redirectTo: 'clients', pathMatch: 'full' }
];

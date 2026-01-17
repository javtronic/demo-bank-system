import { Routes } from '@angular/router';
import { ClientListComponent } from './pages/client-list/client-list.component';
import { ClientFormComponent } from './pages/client-form/client-form.component';

export const CLIENTS_ROUTES: Routes = [
  { path: '', component: ClientListComponent },
  { path: 'new', component: ClientFormComponent },
  { path: 'edit/:clientCode', component: ClientFormComponent }
];
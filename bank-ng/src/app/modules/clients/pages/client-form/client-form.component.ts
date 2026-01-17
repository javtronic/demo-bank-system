import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientService } from '../../../../core/client.service';

@Component({
  selector: 'app-client-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './client-form.component.html',
  styleUrl: './client-form.component.css',
})
export class ClientFormComponent {
  isEdit = false;
  clientCode?: string;
  constructor(
    private fb: FormBuilder,
    private clientService: ClientService,
    private router: Router,
    private route: ActivatedRoute,
  ) {
    this.clientCode =
      this.route.snapshot.paramMap.get('clientCode') ?? undefined;
    this.isEdit = !!this.clientCode;

    if (this.isEdit) {
      this.loadClient();
    }
  }

  form = this.fb.group({
    clientCode: [''],
    password: ['', Validators.required],
    clientStatus: [true],
    person: this.fb.group({
      personCode: [''],
      name: ['', Validators.required],
      gender: ['', Validators.required],
      age: [null as number | null, [Validators.required, Validators.min(1)]],
      identification: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      address: [''],
      status: [true],
    }),
  });

  loadClient() {
    this.clientService.findByCode(this.clientCode!).subscribe((client) => {
      this.form.patchValue(client);
    });
  }

  save() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    if (this.isEdit) {
      this.clientService.update(this.form.value as any).subscribe({
      next: (response) => {
        alert(response);
        this.router.navigate(['/clients']);
      },
      error: (message) => {
        alert(message);
      },
    });
      return;
    }

    const payload = structuredClone(this.form.value);

    delete payload.clientCode;
    delete payload.person?.personCode;

    this.clientService.create(payload as any).subscribe({
      next: (response) => {
        alert(response);
        this.router.navigate(['/clients']);
      },
      error: (message) => {
        alert(message);
      },
    });
  }

  cancel() {
    this.router.navigate(['/clients']);
  }
}

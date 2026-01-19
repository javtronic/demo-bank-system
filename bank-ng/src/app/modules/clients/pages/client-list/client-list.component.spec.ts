import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ClientListComponent } from './client-list.component';

import { of } from 'rxjs';
import { ClientService } from '../../../../core/client.service';
import { Client } from '../../../../models/client.model';

describe('ClientListComponent', () => {

  let component: ClientListComponent;
  let fixture: ComponentFixture<ClientListComponent>;
  let clientServiceMock: jest.Mocked<ClientService>;

  beforeEach(async () => {

    clientServiceMock = {
      getAll: jest.fn()
    } as any;

    await TestBed.configureTestingModule({
      imports: [ClientListComponent],
      providers: [
        { provide: ClientService, useValue: clientServiceMock }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ClientListComponent);
    component = fixture.componentInstance;
  });

  it('should create component', () => {
    expect(component).toBeTruthy();
  });

  it('should load clients on init', () => {

    const mockClients = [
      { clientCode: '1' } as Client,
      { clientCode: '2' } as Client
    ];

    clientServiceMock.getAll.mockReturnValue(of(mockClients));

    component.ngOnInit();

    expect(clientServiceMock.getAll).toHaveBeenCalled();
    expect(component.clients.length).toBe(2);
    expect(component.clients[0].clientCode).toBe('1');
  });

});

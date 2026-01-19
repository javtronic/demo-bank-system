import { TestBed } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { ClientService } from './client.service';
import { ApiResponse } from '../models/api-response.model';
import { Client } from '../models/client.model';

describe('ClientService', () => {
  let service: ClientService;
  let httpMock: HttpTestingController;

  const baseUrl = 'http://localhost:8080/sfk-bank-api/api/v1/clients';

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ClientService],
    });

    service = TestBed.inject(ClientService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should create client and return success message', () => {

    const clientMock = {} as Client;

    const apiResponse: ApiResponse<any> = {
      success: true,
      message: 'Cliente creado correctamente',
      data: null
    };

    service.create(clientMock).subscribe(message => {
      expect(message).toBe('Cliente creado correctamente');
    });

    const req = httpMock.expectOne(baseUrl + '/create');

    expect(req.request.method).toBe('POST');

    req.flush(apiResponse);
  });

  it('should update client and return success message', () => {

    const clientMock = {} as Client;

    const apiResponse: ApiResponse<any> = {
      success: true,
      message: 'Cliente actualizado correctamente',
      data: null
    };

    service.update(clientMock).subscribe(message => {
      expect(message).toBe('Cliente actualizado correctamente');
    });

    const req = httpMock.expectOne(baseUrl + '/update');

    expect(req.request.method).toBe('POST');

    req.flush(apiResponse);
  });

  it('should get client by code', () => {
    const mockResponse = { clientCode: '1' };

    service.findByCode('1').subscribe((res) => {
      expect(res.clientCode).toBe('1');
    });

    const req = httpMock.expectOne(
      baseUrl + '/findByCode?clientCode=1',
    );

    expect(req.request.method).toBe('GET');
    req.flush(mockResponse);
  });

    it('should change client status and return message', () => {

    const clientCode = '123';

    const apiResponse: ApiResponse<any> = {
      success: true,
      message: 'Cambio de estado exitoso',
      data: null
    };

    service.changeStatus(clientCode).subscribe(message => {
      expect(message).toBe('Cambio de estado exitoso');
    });

    const req = httpMock.expectOne(
      `${baseUrl}/changeStatus?clientCode=${clientCode}`
    );

    expect(req.request.method).toBe('GET');

    req.flush(apiResponse);
  });


  it('should get clients list', () => {
    const mockResponse = [{ clientCode: '1' }, { clientCode: '2' }];

    service.getAll().subscribe((res) => {
      expect(res.length).toBe(2);
    });

    const req = httpMock.expectOne(
      baseUrl + '/findAll',
    );

    expect(req.request.method).toBe('GET');
    req.flush(mockResponse);
  });

  it('should handle error when create fails', () => {

    const clientMock = {} as Client;

    service.create(clientMock).subscribe({
      next: () => fail('should fail'),
      error: (error) => {
        expect(error).toBeDefined();
      }
    });

    const req = httpMock.expectOne(baseUrl + '/create');

    req.flush(
      { message: 'Error al crear cliente' },
      { status: 400, statusText: 'Bad Request' }
    );
  });
});

export interface Person {
  personCode?: string;
  name: string;
  gender: 'MASCULINO' | 'FEMENINO';
  age: number;
  identification: string;
  phoneNumber: string;
  address?: string;
  status: boolean;
}

export interface Client {
  clientCode?: string;
  password: string;
  clientStatus: boolean;
  person: Person;
}
export interface Account {
  accountCode?: string;
  clientCode: string;
  accountNumber: string;
  accountType: 'AHORRO' | 'CORRIENTE';
  initialBalance: number;
  accountStatus: boolean;
}
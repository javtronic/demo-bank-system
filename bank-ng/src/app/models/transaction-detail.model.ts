export interface TransactionDetail {
  transactionCode: string;
  transactionDate: string;
  transactionType: 'CREDITO' | 'DEBITO';
  amount: number;
  balance: number;

  accountCode: string;
  accountNumber: string;
  accountType: string;
  initialBalance: number;
  accountStatus: boolean;

  name: string;
}

export interface TransactionRequest {
  accountCode: string;
  transactionType: 'CREDITO' | 'DEBITO';
  amount: number;
}
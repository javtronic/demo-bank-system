export interface ReportResponse {
  clientCode: string;
  clientName: string;
  accountReportVOS: AccountReport[];
  pdfBase64?: string;
}

export interface AccountReport {
  accountNumber: string;
  accountType: string;
  initialBalance: number;
  accountStatus: boolean;
  transactions: TransactionReport[];
  totalCredits: number;
  totalDebits: number;
}

export interface TransactionReport {
  transactionDate: string;
  transactionType: 'CREDITO' | 'DEBITO';
  amount: number;
  balance: number;
}

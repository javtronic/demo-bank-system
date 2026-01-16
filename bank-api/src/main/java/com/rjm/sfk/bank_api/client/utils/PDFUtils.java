package com.rjm.sfk.bank_api.client.utils;

import com.rjm.sfk.bank_api.client.enums.TransactionType;
import com.rjm.sfk.bank_api.vo.report.AccountReportVO;
import com.rjm.sfk.bank_api.vo.report.ReportVO;
import com.rjm.sfk.bank_api.vo.report.TransactionReportVO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Base64;

/**
 *
 */
public class PDFUtils {

    /**
     * Generates a PDF report with the given report VO and returns it as a Base64 string.
     *
     * @param report the report VO
     * @return the report as a Base64 string
     * @throws IOException if an error occurs generating the PDF
     */
    public  static String generatePdfBase64(ReportVO report) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream content = new PDPageContentStream(document, page);
            PDFont font = PDType1Font.HELVETICA;
            float y = 750;
            float margin = 50;

            // Título
            content.beginText();
            content.setFont(font, 14);
            content.newLineAtOffset(margin, y);
            content.showText("Estado de Cuenta - " + report.clientName());
            content.endText();
            y -= 25;

            // Cabecera
            content.beginText();
            content.setFont(font, 10);
            content.newLineAtOffset(margin, y);
            content.showText("Fecha | Cuenta | Tipo | Saldo Inicial | Estado | Movimiento | Saldo");
            content.endText();
            y -= 15;

            // Datos
            for (AccountReportVO account : report.accountReportVOS()) {
                for (TransactionReportVO tx : account.transactions()) {
                    content.beginText();
                    content.setFont(font, 10);
                    content.newLineAtOffset(margin, y);

                    String line = String.format(
                            "%s | %s | %s | %.2f | %s | %s | %.2f",
                            Instant.ofEpochMilli(tx.getTransactionDate().getTime())
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate(),
                            account.accountNumber(),
                            account.accountType(),
                            account.initialBalance(),
                            account.accountStatus(),
                            formatTransactionAmount(tx.getTransactionType(), tx.getAmount()),
                            tx.getBalance()
                    );

                    content.showText(line);
                    content.endText();
                    y -= 15;
                    if (y < 50) {
                        content.close();
                        page = new PDPage();
                        document.addPage(page);
                        content = new PDPageContentStream(document, page);
                        y = 750;
                    }
                }
            }

            content.close();

            // Convertir PDF a Base64
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                document.save(out);
                return Base64.getEncoder().encodeToString(out.toByteArray());
            }
        }
    }

    /**
     * Formats a transaction amount as a string, adding a minus sign if the transaction type is debit.
     *
     * @param type the transaction type
     * @param amount the transaction amount
     * @return the formatted transaction amount as a string
     */
    private static String formatTransactionAmount(TransactionType type, BigDecimal amount) {
        // Format the number with 2 decimal places
        String formattedAmount = String.format("%.2f", amount);
        if (TransactionType.DEBITO.equals(type)) {
            return "-" + formattedAmount;
        }
        return formattedAmount;
    }

}

package com.rjm.sfk.bank_api.vo.report;

import java.util.List;

/**
 * Report VO.
 *
 * @param clientCode
 * @param clientName
 * @param accountReportVOS
 */
public record ReportVO(
        String clientCode,
        String clientName,
        List<AccountReportVO> accountReportVOS
) { }

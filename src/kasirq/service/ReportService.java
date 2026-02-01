//package kasirq.service;
//
//import kasirq.config.DatabaseConfig;
//import kasirq.dao.ReportDAO;
//import kasirq.util.ExcelUtil;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.List;
//
//public class ReportService {
//
//    /**
//     * Generate daily Excel report
//     */
//    public void generateDailyReport(LocalDate date) throws Exception {
//
//        if (date == null) {
//            throw new IllegalArgumentException("Date is required");
//        }
//
//        try (Connection conn = DatabaseConfig.getConnection()) {
//
//            ReportDAO reportDAO = new ReportDAO(conn);
//
//            Date sqlDate = Date.valueOf(date);
//
//            List<Object[]> headers =
//                    reportDAO.getDailyTransactionSummary(sqlDate);
//
//            List<Object[]> details =
//                    reportDAO.getDailyTransactionDetails(sqlDate);
//
//            ExcelUtil.exportDailyReport(
//                    date,
//                    headers,
//                    details
//            );
//        }
//    }
//}

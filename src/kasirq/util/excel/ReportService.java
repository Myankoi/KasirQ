/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirq.util.excel;

/**
 *
 * @author RAMADIAN
 */

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import kasirq.config.DatabaseConfig;
import kasirq.util.excel.ReportDAO;
import kasirq.util.excel.dto.*;

public class ReportService {

    public DailySummaryDTO getSummary(LocalDate date) throws Exception {
        try (Connection c = DatabaseConfig.getConnection()) {
            return new ReportDAO(c).getDailySummary(date);
        }
    }

    public List<TransactionReportDTO> getTransactions(LocalDate date)
            throws Exception {
        try (Connection c = DatabaseConfig.getConnection()) {
            return new ReportDAO(c).getTransactions(date);
        }
    }

    public List<TransactionDetailReportDTO> getDetails(LocalDate date)
            throws Exception {
        try (Connection c = DatabaseConfig.getConnection()) {
            return new ReportDAO(c).getTransactionDetails(date);
        }
    }

    public List<ProductSummaryDTO> getProductSummary(LocalDate date)
            throws Exception {
        try (Connection c = DatabaseConfig.getConnection()) {
            return new ReportDAO(c).getProductSummary(date);
        }
    }
}
package kasirq.util.excel;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import kasirq.util.excel.dto.*;

public class ExcelReportExporter {

    private final XSSFWorkbook workbook;
    private final CellStyle headerStyle;
    private final CellStyle moneyStyle;
    private final CellStyle zebraStyle;
    private final CellStyle titleStyle;
    private final CellStyle labelStyle;
    private final CellStyle valueStyle;

    public ExcelReportExporter() {
        workbook = new XSSFWorkbook();

        // 1. STYLE: JUDUL UTAMA (Besar & Center)
        titleStyle = workbook.createCellStyle();
        Font titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 18);
        titleFont.setColor(IndexedColors.DARK_BLUE.getIndex());
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.LEFT);

        // 2. STYLE: HEADER TABEL (Modern Midnight Blue)
        headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.DARK_TEAL.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font hFont = workbook.createFont();
        hFont.setBold(true);
        hFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(hFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        applyFullBorders(headerStyle);

        // 3. STYLE: LABEL SUMMARY (Bold Grey)
        labelStyle = workbook.createCellStyle();
        Font lFont = workbook.createFont();
        lFont.setBold(true);
        labelStyle.setFont(lFont);
        labelStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        labelStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        applyFullBorders(labelStyle);

        // 4. STYLE: VALUE SUMMARY
        valueStyle = workbook.createCellStyle();
        applyFullBorders(valueStyle);

        // 5. STYLE: ZEBRA (Light Blue-ish Grey)
        zebraStyle = workbook.createCellStyle();
        zebraStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        zebraStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        applyFullBorders(zebraStyle);

        // 6. STYLE: CURRENCY (Format Rupiah Standar)
        moneyStyle = workbook.createCellStyle();
        DataFormat df = workbook.createDataFormat();
        moneyStyle.setDataFormat(df.getFormat("#,##0"));
        applyFullBorders(moneyStyle);
    }

    private void applyFullBorders(CellStyle style) {
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
    }

    public void exportDailyReport(String filePath, LocalDate date, DailySummaryDTO summary,
            List<TransactionReportDTO> transactions, List<TransactionDetailReportDTO> details,
            List<ProductSummaryDTO> products) throws Exception {

        createSummarySheet(date, summary);
        createTableSheet("Data Transaksi", new String[]{"ID TX", "Waktu", "Kasir", "Pelanggan", "Kelas", "Item", "Total Bayar", "Metode"}, transactions);
        createTableSheet("Detail Penjualan", new String[]{"ID TX", "Nama Produk", "Kategori", "Qty", "Harga", "Subtotal"}, details);
        createTableSheet("Statistik Produk", new String[]{"Produk", "Kategori", "Terjual", "Total Omzet"}, products);

        try (FileOutputStream out = new FileOutputStream(filePath)) {
            workbook.write(out);
        } finally {
            workbook.close();
        }
    }

    private void createSummarySheet(LocalDate reportDate, DailySummaryDTO s) {
        XSSFSheet sheet = workbook.createSheet("Dashboard");
        sheet.setDisplayGridlines(false); // Menghilangkan garis bantu kotak-kotak Excel

        // HEADER LAPORAN
        Row r0 = sheet.createRow(1);
        Cell cTitle = r0.createCell(1);
        cTitle.setCellValue("DAILY SALES PERFORMANCE REPORT");
        cTitle.setCellStyle(titleStyle);

        Row r1 = sheet.createRow(2);
        Cell cSub = r1.createCell(1);
        cSub.setCellValue("Dibuat pada: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));

        // DASHBOARD BOX (Row 4 s/d 10)
        String[][] metrics = {
            {"PARAMETER", "NILAI STATISTIK"},
            {"Tanggal Laporan", reportDate.toString()},
            {"Volume Transaksi", s.getTotalTransaction() + " Transaksi"},
            {"Unit Terjual", s.getTotalQty() + " Unit"},
            {"Total Revenue", "Rp " + String.format("%,.0f", s.getTotalRevenue())},
            {"Produk Terlaris", s.getTopProduct()},
            {"Kategori Utama", s.getTopCategory()}
        };

        for (int i = 0; i < metrics.length; i++) {
            Row row = sheet.createRow(i + 4);
            Cell cLabel = row.createCell(1);
            Cell cVal = row.createCell(2);
            
            cLabel.setCellValue(metrics[i][0]);
            cVal.setCellValue(metrics[i][1]);
            
            cLabel.setCellStyle(i == 0 ? headerStyle : labelStyle);
            cVal.setCellStyle(i == 0 ? headerStyle : valueStyle);
        }

        sheet.autoSizeColumn(1);
        sheet.setColumnWidth(2, 8000); // Set lebar kolom nilai manual agar lega
    }

    private void createTableSheet(String name, String[] headers, List<?> list) {
        XSSFSheet sheet = workbook.createSheet(name);
        
        // Header Row
        Row headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints(25);
        for (int i = 0; i < headers.length; i++) {
            Cell c = headerRow.createCell(i);
            c.setCellValue(headers[i]);
            c.setCellStyle(headerStyle);
        }

        // Data Rows
        int rowIdx = 1;
        for (Object obj : list) {
            Row r = sheet.createRow(rowIdx++);
            r.setHeightInPoints(18);
            CellStyle currentBase = (rowIdx % 2 == 0) ? zebraStyle : valueStyle;
            
            if (obj instanceof TransactionReportDTO) {
                TransactionReportDTO t = (TransactionReportDTO) obj;
                fillRow(r, currentBase, t.getTransactionId(), t.getCreatedAt().toString().substring(11, 16), 
                        t.getCashier(), t.getBuyerName(), t.getBuyerClass(), t.getTotalProduct(), t.getTotalPrice(), t.getPaymentMethod());
            } else if (obj instanceof TransactionDetailReportDTO) {
                TransactionDetailReportDTO d = (TransactionDetailReportDTO) obj;
                fillRow(r, currentBase, d.getTransactionId(), d.getProductName(), d.getCategoryName(), d.getQty(), d.getPrice(), d.getSubTotal());
            } else if (obj instanceof ProductSummaryDTO) {
                ProductSummaryDTO p = (ProductSummaryDTO) obj;
                fillRow(r, currentBase, p.getProductName(), p.getCategoryName(), p.getTotalSold(), p.getRevenue());
            }
        }

        // Final Touch: Auto Size & Freeze Panes
        for (int i = 0; i < headers.length; i++) sheet.autoSizeColumn(i);
        sheet.createFreezePane(0, 1); // Baris header tetap terlihat saat di-scroll
    }

    private void fillRow(Row r, CellStyle baseStyle, Object... values) {
        for (int i = 0; i < values.length; i++) {
            Cell c = r.createCell(i);
            if (values[i] instanceof Number) {
                c.setCellValue(((Number) values[i]).doubleValue());
                // Jika angka besar, asumsikan itu uang/harga
                if (((Number) values[i]).doubleValue() > 1000) {
                    // Clone style agar tidak merusak baseStyle zebra/putih
                    CellStyle mStyle = workbook.createCellStyle();
                    mStyle.cloneStyleFrom(baseStyle);
                    mStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0"));
                    c.setCellStyle(mStyle);
                } else {
                    c.setCellStyle(baseStyle);
                }
            } else {
                c.setCellValue(values[i] != null ? String.valueOf(values[i]) : "-");
                c.setCellStyle(baseStyle);
            }
        }
    }
}
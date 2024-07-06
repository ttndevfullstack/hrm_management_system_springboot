package sushine_group.hrm_management_system.service;

import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sushine_group.hrm_management_system.model.ChamCong;
import sushine_group.hrm_management_system.model.ChamCongId;
import sushine_group.hrm_management_system.model.NhanVien;
import sushine_group.hrm_management_system.repository.ChamCongRepository;
import sushine_group.hrm_management_system.repository.NhanVienRepository;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class ChamCongService {

    private final ChamCongRepository chamCongRepository;
    private final NhanVienRepository nhanVienRepository;

    public List<ChamCong> getAllChamCong() {
        return chamCongRepository.findAll(); // Example method; adjust as per your repository
    }

    @Autowired
    public ChamCongService(ChamCongRepository chamCongRepository, NhanVienRepository nhanVienRepository) {
        this.chamCongRepository = chamCongRepository;
        this.nhanVienRepository = nhanVienRepository;
    }

    @Transactional
    public void importChamCongDataFromExcel(MultipartFile file) throws IOException, ParseException {
        List<ChamCong> chamCongList = readChamCongFromExcel(file.getInputStream());

        for (ChamCong chamCong : chamCongList) {
            // Fetch NhanVien by ID and set it to ChamCong
            String idNV = chamCong.getId().getIdNV(); // Assuming getIdNV() returns employee ID as string
            NhanVien nhanVien = nhanVienRepository.findById(idNV).orElse(null);

            if (nhanVien == null) {
                // Handle case where NhanVien with given ID is not found
                // For example, log an error, skip this entry, or throw an exception
                continue;
            }

            chamCong.setNhanVien(nhanVien);
        }

        chamCongRepository.saveAll(chamCongList);
    }

    private List<ChamCong> readChamCongFromExcel(InputStream inputStream) throws IOException, ParseException {
        List<ChamCong> chamCongList = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat monthYearFormat = new SimpleDateFormat("MMyyyy");

        // Skip header row if needed
        if (rows.hasNext()) {
            rows.next(); // Skipping header row
        }

        while (rows.hasNext()) {
            Row currentRow = rows.next();
            ChamCong chamCong = new ChamCong();
            ChamCongId chamCongId = new ChamCongId();

            // Read employee ID from first column assuming it's a string
            Cell idCell = currentRow.getCell(0);
            String idNV = idCell != null ? idCell.getStringCellValue() : null;
            if (idNV == null || idNV.isEmpty()) {
                // Handle null or empty employee ID
                continue; // Skip this row or throw an exception as needed
            }
            chamCongId.setIdNV(idNV);

            // Parse date from the second column
            Cell dateCell = currentRow.getCell(1);
            Date ngayLamViec = null;
            if (dateCell != null) {
                if (dateCell.getCellType() == CellType.NUMERIC) {
                    if (DateUtil.isCellDateFormatted(dateCell)) {
                        ngayLamViec = dateCell.getDateCellValue();
                    } else {
                        // Handle non-date numeric value if necessary
                        // Example: ngayLamViec = handleNonDateNumericValue(dateCell.getNumericCellValue());
                    }
                } else if (dateCell.getCellType() == CellType.STRING) {
                    ngayLamViec = dateFormat.parse(dateCell.getStringCellValue());
                }
            }
            if (ngayLamViec == null) {
                // Handle case where date is null or invalid
                // Example: throw new ParseException("Invalid date format", 0);
            }

            // Convert date to month and year integer format
            int monthYear = Integer.parseInt(monthYearFormat.format(ngayLamViec));
            chamCongId.setId(monthYear);

            // Set other numeric cell values
            chamCong.setThangNam(currentRow.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getDateCellValue());
            chamCong.setSoNgayLam((int) currentRow.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());
            chamCong.setSoNgayNghi((int) currentRow.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());
            chamCong.setSoLanTre((int) currentRow.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());

            // Set composite primary key and add to list
            chamCong.setId(chamCongId);
            chamCongList.add(chamCong);
        }

        workbook.close();
        return chamCongList;
    }

    public List<ChamCong> getChamCongByMonth(YearMonth selectedMonth) {
        int year = selectedMonth.getYear();
        int month = selectedMonth.getMonthValue();
        return chamCongRepository.findByMonth(year, month);
    }
}

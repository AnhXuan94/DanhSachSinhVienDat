import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DanhSachDat {

    // Lọc SV điểm >= 5, sắp xếp giảm dần theo điểm (trùng điểm thì tăng dần theo mã).
    public static String danhSachDat(String s) {
        if (s == null || s.trim().isEmpty()) return "KHONG CO";

        List<String> listDat = new ArrayList<>();
        String[] parts = s.split(",");

        // Tách và lọc
        for (String part : parts) {
            String[] info = part.trim().split(":");
            if (info.length == 2) {
                try {
                    double diem = Double.parseDouble(info[1].trim());
                    if (diem >= 5) {
                        listDat.add(part.trim());
                    }
                } catch (NumberFormatException e) { /* Bỏ qua */ }
            }
        }

        if (listDat.isEmpty()) return "KHONG CO";

        // Điểm giảm dần, Mã tăng dần
        Collections.sort(listDat, (a, b) -> {
            String[] infoA = a.split(":");
            String[] infoB = b.split(":");
            double diemA = Double.parseDouble(infoA[1]);
            double diemB = Double.parseDouble(infoB[1]);

            if (diemA != diemB) return Double.compare(diemB, diemA); // Giảm dần
            return infoA[0].compareTo(infoB[0]); // Tăng dần
        });

        // Tạo chuỗi kết quả
        StringBuilder sb = new StringBuilder();
        for (String sv : listDat) {
            sb.append(sv.split(":")[0]).append(" ");
        }
        return sb.toString().trim();
    }

}
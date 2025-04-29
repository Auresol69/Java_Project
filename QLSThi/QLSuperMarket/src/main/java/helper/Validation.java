package helper;

import java.util.regex.Pattern;

public class Validation {

    public static Boolean isEmpty(String input) {
        if (input == null) {
            return true;
        }
        return input.equals("");
    }

    public static Boolean isEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    public static boolean isNumber(String num) {
        boolean result = true;
        if (num == null) return false;
        try {
            long k = Long.parseLong(num);
            if(k < 0) {
                result = false;
            }
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }
    public static boolean isFax(String fax) {
        if (fax == null || fax.trim().isEmpty()) {
            return false;
        }
        // Cho phép các ký tự số, khoảng trắng, dấu +, -, (, )
        String faxRegex = "^[\\d\\s\\-()+]+$";
        if (!Pattern.matches(faxRegex, fax)) {
            return false;
        }
        
        // Đếm số chữ số thật sự (bỏ các ký tự đặc biệt)
        String digitsOnly = fax.replaceAll("\\D", ""); // Loại bỏ tất cả không phải số
        return digitsOnly.length() >= 7 && digitsOnly.length() <= 15;
    }
    
}

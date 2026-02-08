package kasirq.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtil {

    private static final Locale LOCALE_ID = new Locale("id", "ID");

    private CurrencyUtil() {
        // prevent instantiation
    }

    public static String toRupiah(BigDecimal value) {
        if (value == null) {
            return "Rp 0";
        }
        NumberFormat formatter = NumberFormat.getCurrencyInstance(LOCALE_ID);
        return formatter.format(value);
    }

    public static String toRupiah(long value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(LOCALE_ID);
        return formatter.format(value);
    }
}

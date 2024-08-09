package com.wealth.telegram.utils;

import com.wealth.telegram.constant.ValueConstant;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtils {

    public static String formatAmountStr(String amountStr, String currency) {
        if(amountStr == null || amountStr.isEmpty()) {
            return amountStr;
        }
        Double amount = Double.parseDouble(amountStr);
        return formatAmount(amount, currency);
    }

    public static String formatAmount(Double amount, String currency) {
        NumberFormat format = NumberFormat.getNumberInstance(Locale.getDefault());
        if (currency.equals(ValueConstant.KHR)) {
            format.setMaximumFractionDigits(0);
            format.setRoundingMode(RoundingMode.DOWN);
        } else {
            format.setMinimumFractionDigits(2);
        }
        return format.format(amount);
    }

}

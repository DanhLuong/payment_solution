package org.example.utils.common;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    private Utils() {}
    public static <T> Method getMethodFromFieldName(String fieldName, Class<T> clazz) {
        if(fieldName == null || fieldName.isEmpty()) {
            throw new IllegalArgumentException("Field is empty or null");
        }
        String getterName = String.format("get%s%s",
                fieldName.substring(0, 1).toUpperCase(),
                fieldName.substring(1));
        Method method = null;
        try {
            method = clazz.getMethod(getterName);
        } catch (NoSuchMethodException e) {
            System.out.println("No Field Named [" + fieldName + "] in class " + clazz.getName());
        }
        return method;
    }

    public static ZonedDateTime convertZonedDateTimeFromString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inputDate = LocalDate.parse(date, formatter);
        return inputDate.atStartOfDay(ZoneId.systemDefault());
    }

    public static ZonedDateTime convertToStandardHour(ZonedDateTime date, int hour) {
        return ZonedDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), hour, 0, 0, 0, date.getZone());
    }

}

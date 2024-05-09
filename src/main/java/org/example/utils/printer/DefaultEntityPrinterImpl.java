package org.example.utils.printer;

import org.example.utils.common.Utils;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;

public class DefaultEntityPrinterImpl<T> implements EntityPrinter<T> {
    private static final int DEFAULT_COLUMN_WIDTH = 15;
    private static final String DEFAULT_NUMBER_FORMAT = "d";
    private static final String DEFAULT_DATE_FORMAT = "tD";
    private static final String DEFAULT_STRING_FORMAT = "s";
    private static final String DEFAULT_BIG_DECIMAL_FORMAT = ".0f";

    private final Class<T> clazz;
    private final List<ColumnConfig> tableConfig;
    private boolean blocked = false;

    private static class ColumnConfig {
        public String colHeader;
        public Method colGetDataMethod;
        public String format;
        //rawFormat follow https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html remove the [width] part
        public ColumnConfig(String colHeader, Method colGetDataMethod, String format) {
            this.colHeader = colHeader;
            this.colGetDataMethod = colGetDataMethod;
            this.format = format;
        }
    }

    public DefaultEntityPrinterImpl(Class<T> clazz) {
        this.clazz = clazz;
        this.tableConfig = new ArrayList<>();
    }
    @Override
    public void registerColumn(String headerName, String fieldName, String format) {
        if (blocked) {
            return;
        }
        Method getDataMethod = Utils.getMethodFromFieldName(fieldName, clazz);
        ColumnConfig colConfig = new ColumnConfig(headerName, getDataMethod, format);
        tableConfig.add(colConfig);
    }

    @Override
    public void registerColumn(String headerName, String fieldName, Integer width) {
        Class<?> returnDataType = Utils.getMethodFromFieldName(fieldName, clazz).getReturnType();
        String format = "%-" + width;
        if(BigDecimal.class.isAssignableFrom(returnDataType)) {
            format += DEFAULT_BIG_DECIMAL_FORMAT;
        }else if(Number.class.isAssignableFrom(returnDataType)) {
            format += DEFAULT_NUMBER_FORMAT;
        } else if(Date.class.isAssignableFrom(returnDataType) ||
                LocalDateTime.class.isAssignableFrom(returnDataType) ||
                ZonedDateTime.class.isAssignableFrom(returnDataType)) {
            format += DEFAULT_DATE_FORMAT;
        } else {
            format += DEFAULT_STRING_FORMAT;
        }
        registerColumn(headerName, fieldName, format);
    }

    @Override
    public void registerColumn(String headerName, String fieldName) {
        registerColumn(headerName, fieldName, DEFAULT_COLUMN_WIDTH);
    }

    @Override
    public void print(PrintStream ps, List<T> entities) {
        StringBuilder sb = new StringBuilder();
        for(ColumnConfig col : tableConfig) {
            sb.append(String.format("%-15s",col.colHeader));
        }
        sb.append("\n");
        for(T entity : entities) {
            for(ColumnConfig col : tableConfig) {
                Class<?> returnType = col.colGetDataMethod.getReturnType();
                Object data = null;
                try {
                    data = col.colGetDataMethod.invoke(entity);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println("Error when printing. PLease check config!");
                    throw new RuntimeException(e);
                }
                sb.append(String.format(col.format, data));
            }
            sb.append("\n");
        }
        ps.println(sb);
    }
    public void doneConfig() {
        this.blocked = true;
    }
}

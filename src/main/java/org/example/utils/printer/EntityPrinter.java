package org.example.utils.printer;

import java.io.PrintStream;
import java.util.List;

public interface EntityPrinter<T> {
    void registerColumn(String headerName, String fieldName);
    void registerColumn(String headerName, String fieldName, Integer width);
    void registerColumn(String headerName, String fieldName, Integer width, String format);
    String getPrintResult(List<T> entities);
    void doneConfig();
}

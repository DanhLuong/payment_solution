package org.example.utils.printer;

public interface PrinterManager {
    void addPrinter(String name, EntityPrinter printer);
    EntityPrinter getPrinter(String data);
    void doneConfig();
}

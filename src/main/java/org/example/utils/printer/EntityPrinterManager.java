package org.example.utils.printer;

import java.util.HashMap;
import java.util.Map;

public class EntityPrinterManager implements PrinterManager{
    private final Map<String, EntityPrinter> data= new HashMap<>();
    private boolean blocked = false;
    @Override
    public void addPrinter(String name, EntityPrinter printer) {
        if(blocked || printer == null) {
            return;
        }
        data.put(name, printer);
    }

    @Override
    public EntityPrinter getPrinter(String name) {
        return this.data.getOrDefault(name.trim(), null);
    }

    @Override
    public void doneConfig() {
        this.blocked = true;
    }
}

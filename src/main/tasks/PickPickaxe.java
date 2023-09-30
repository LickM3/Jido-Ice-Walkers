package main.tasks;

import com.hazion.api.Hotbar;
import com.hazion.api.Inventory;
import com.hazion.api.script.Task;

public class PickPickaxe implements Task {

    @Override
    public boolean verify() {
        return !Inventory.getSelectedSlot().getItemStack().getName().endsWith("pickaxe");
    }

    @Override
    public int execute() {
        if (Hotbar.contains(item -> item.getName().endsWith("pickaxe"))) {
            Hotbar.select(item -> item.getName().endsWith("pickaxe"));
        } else {
            Inventory.open();
            Inventory.selectItem(item -> item.getName().endsWith("pickaxe"));
            Hotbar.select(item -> item.getName().endsWith("pickaxe"));
        }
        return 200;
    }
}

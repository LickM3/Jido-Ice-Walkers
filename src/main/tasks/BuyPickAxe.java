package main.tasks;

import com.hazion.api.Inventory;
import com.hazion.api.hypixel.skyblock.SkyBlockMerchants;
import com.hazion.api.script.Task;

public class BuyPickAxe implements Task {

    @Override
    public boolean verify() {
        return !Inventory.contains(item -> item.getName().endsWith("pickaxe"));
    }

    @Override
    public int execute() {
        // Doesnt works untill api update
        System.out.println("You have no pickaxe. Lets buy it!");
        SkyBlockMerchants.openMerchant(SkyBlockMerchants.Merchant.MINE_MERCHANT);
        SkyBlockMerchants.buyItem(item -> item.getCustomName().contains("Promising"));
        return 500;
    }
}

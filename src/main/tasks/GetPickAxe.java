package main.tasks;
import com.hazion.api.Inventory;
import com.hazion.api.hypixel.skyblock.SkyBlockMerchants;
import com.hazion.api.script.Task;

public class GetPickAxe implements Task {

    @Override
    public boolean verify() {
        return !Inventory.contains(item -> item.getName().endsWith("pickaxe"));
    }

    @Override
    public int execute() {
        SkyBlockMerchants.goToMerchant(SkyBlockMerchants.Merchant.MINE_MERCHANT);
        return 2000;
    }
}

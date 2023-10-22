package main.tasks;
import com.hazion.api.Inventory;
import com.hazion.api.hypixel.skyblock.SkyBlockMerchants;
import com.hazion.api.script.Task;
import com.hazion.api.utils.PlayerHelper;
import main.Main;

public class GetPickAxe implements Task {

    @Override
    public boolean verify() {
        return !Inventory.contains(item -> item.getName().endsWith("pickaxe")) && PlayerHelper.playerFeet().getDistance(SkyBlockMerchants.Merchant.MINE_MERCHANT.getBlockPos().getX(), SkyBlockMerchants.Merchant.MINE_MERCHANT.getBlockPos().getY(), SkyBlockMerchants.Merchant.MINE_MERCHANT.getBlockPos().getZ()) > 4;
    }

    @Override
    public int execute() {
        Main.currTask = "Walking to Mine Merchant";
        SkyBlockMerchants.goToMerchant(SkyBlockMerchants.Merchant.MINE_MERCHANT);
        return 2000;
    }
}

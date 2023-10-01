package main.tasks;

import com.hazion.api.Chat;
import com.hazion.api.Inventory;
import com.hazion.api.camera.Camera;
import com.hazion.api.hypixel.skyblock.SkyBlock;
import com.hazion.api.hypixel.skyblock.SkyBlockMerchants;
import com.hazion.api.pathfinder.movement.MovementHandler;
import com.hazion.api.script.Task;
import com.hazion.api.world.blocks.BlockPos;
import main.Main;

public class BuyPickAxe implements Task {

    @Override
    public boolean verify() {
        return !Inventory.contains(item -> item.getName().endsWith("pickaxe"));
    }

    @Override
    public int execute() {
        Main.currTask = "Buying new pickaxe";
        if (SkyBlock.getPurseCoins() >= 35) {
            if (!SkyBlockMerchants.isMenuOpen()) {
                // BlockPos merchantHead = new BlockPos(SkyBlockMerchants.Merchant.MINE_MERCHANT.getBlockPos().getX(), SkyBlockMerchants.Merchant.MINE_MERCHANT.getBlockPos().getY() + 1, SkyBlockMerchants.Merchant.MINE_MERCHANT.getBlockPos().getZ());
                Camera.lockOnto(new BlockPos(9, 70, -125));
                MovementHandler.walkTo(new BlockPos(-11, 68, -125));
                SkyBlockMerchants.openMerchant(SkyBlockMerchants.Merchant.MINE_MERCHANT);
                Camera.unlock();
            }
            SkyBlockMerchants.buyItem(item -> item.getCustomName().contains("Promising"));
            return 2000;
        } else {
            float need = 35 - SkyBlock.getPurseCoins();
            Chat.addMessage(Chat.Level.WARNING, "You dont have enough coins to buy new pickaxe. You need " + need + " more coins!");
            return 2000;
        }

    }
}

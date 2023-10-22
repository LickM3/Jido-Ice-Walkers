package main.tasks;

import com.hazion.api.hypixel.skyblock.SkyBlockSection;
import com.hazion.api.pathfinder.movement.MovementHandler;
import com.hazion.api.script.Task;
import main.Main;

public class GoToIceWalkers implements Task {

    @Override
    public boolean verify() {
        return !SkyBlockSection.getCurrentSkyBlockSection().equals(SkyBlockSection.GREAT_ICE_WALL);
    }

    @Override
    public int execute() {
        Main.currTask = "Walking to Ice Walkers area";
        MovementHandler.walkTo(SkyBlockSection.GREAT_ICE_WALL);
        return 1000;
    }
}

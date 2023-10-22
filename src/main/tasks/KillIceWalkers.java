package main.tasks;

import com.hazion.api.Chat;
import com.hazion.api.Entities;
import com.hazion.api.camera.Camera;
import com.hazion.api.input.Input;
import com.hazion.api.pathfinder.movement.MovementHandler;
import com.hazion.api.pathing.Area;
import com.hazion.api.peer.world.entity.Entity;
import com.hazion.api.peer.world.player.RemotePlayer;
import com.hazion.api.script.Task;
import com.hazion.api.utils.PlayerHelper;
import com.hazion.api.world.blocks.BlockPos;
import main.Main;

public class KillIceWalkers implements Task {

    @Override
    public boolean verify() {
        if (Entities.getAll(entity -> entity.getName().contains("Ice Walker") && entity.isAlive()).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int execute() {
            Main.currTask = "Killing Ice Walkers";
            // IW area should be updated in future. Needs more testing.

                Entity closestIceWalker = Entities.getClosest(iw -> iw instanceof RemotePlayer && iw.isAlive() && iw.getName().contains("Ice Walker") && Area.rectangular(-27.7, 25.7, 127.0, 140.0, 147.7, 177.7).contains(iw));
                BlockPos whereStanding = PlayerHelper.getStandingOn();
                Camera.lockOnto(closestIceWalker, true);
                do {
                    Input.MOVE_FORWARD.setHoldingDown(true);
                    if (PlayerHelper.playerFeet().getDistance(closestIceWalker.getStandingOn().getX(), closestIceWalker.getStandingOn().getY(), closestIceWalker.getStandingOn().getZ()) < 4) {
                        Input.CLICK_LEFT.click();
                    }
                    if (!Area.rectangular(-27.7, 25.7, 127.0, 140.0, 147.7, 177.7).contains(closestIceWalker)) {
                        return 100;
                    }
                    if (PlayerHelper.playerFeet().getDistance(whereStanding.getX(), whereStanding.getY(), whereStanding.getZ()) < 1 && closestIceWalker.isAlive()) {
                        return 100;
                    }
                }while(closestIceWalker.isAlive());

                if (!closestIceWalker.isAlive() || PlayerHelper.playerFeet().getDistance(whereStanding.getX(), whereStanding.getY(), whereStanding.getZ()) < 3) {
                    Chat.addMessage(Chat.Level.WARNING, "Ice Walker got killed. Movement should stop!");
                    Input.MOVE_FORWARD.setHoldingDown(false);
                }

                if (!closestIceWalker.isAlive() && PlayerHelper.playerFeet().getDistance(closestIceWalker.getStandingOn().getX(), closestIceWalker.getStandingOn().getY(), closestIceWalker.getStandingOn().getZ()) < 4) {
                    Main.kills++;
                }

                return 500;
                }

    }

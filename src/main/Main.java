package main;

import java.awt.Color;

import com.hazion.api.render.JidoRenderer;
import com.hazion.api.script.Manifest;
import com.hazion.api.script.Script;
import com.hazion.api.utils.Timer;
import main.tasks.*;
import java.util.ArrayList;
import com.hazion.api.script.Task;

// Future updates: Safespot feature, xp tracker, iw area improvements, config screen.

@Manifest(name = "Lick's Ice Walkers", author = "LickM3", version = 0.1, description = "Farms Ice Walkers for you to gain easy combat XP in early game.")
public class Main implements Script {

    private ArrayList<Task> tasks = new ArrayList<>();
    public static int kills = 0;
    Timer scriptTimer = new Timer();
    public static String currTask = "Loading...";


    @Override
    public void onStart() {
        // BuyPickaxe doesnt work (prob should work after api update)
        tasks.add(new GetPickAxe());
        tasks.add(new BuyPickAxe());
        tasks.add(new GoToIceWalkers());
        tasks.add(new PickPickaxe());
        tasks.add(new KillIceWalkers());

        scriptTimer.start();
    }

    @Override
    public int poll() {
        for(Task t : tasks){
            if(t.verify()){
                return t.execute();
            }
        }
        return 100;
    }


    @Override
    public void onRender() {
        // Add xp display when possible.
        JidoRenderer.drawLine(10, 115, 10, 178, 4, Color.CYAN);
        JidoRenderer.drawText("Lick's Ice Walkers 0.1", 14, 120, Color.CYAN);
        JidoRenderer.drawText("Timer: ".concat(scriptTimer.getFormattedTime()), 14, 135, Color.WHITE);
        JidoRenderer.drawText("Kills: ".concat(String.valueOf(kills)), 14, 150, Color.WHITE);
        JidoRenderer.drawText("Task: ".concat(currTask), 14, 165, Color.WHITE);
    }

    @Override
    public String server() {
        return "mc.hypixel.net";
    }
}
package me.sebbaindustries.advancedafk.detection;

import me.sebbaindustries.advancedafk.Core;
import me.sebbaindustries.advancedafk.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;

public class ThreadManager extends Analysis {

    private int analysisClock = 0;

    public ThreadManager() {
        initializeLoop();
    }

    protected void initializeLoop() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                analysisClock++;
                loopOnlinePlayers();
            }
        }, 0, 1000);
    }

    private void loopOnlinePlayers() {
        CompletableFuture.runAsync(() -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                // Update players location
                Core.gCore.playerData.updateLocation(p);
                /*
                Analysis
                 */
                if (analysisClock >= 15) {
                    new Analysis().startAnalysis(p);
                }
            }
            if (analysisClock >= 15) {
                analysisClock = 0;
                kickAfkPlayers();
            }
        });
    }

    private void kickAfkPlayers() {
        // check for online players
        if (!(Bukkit.getOnlinePlayers().size() >= Core.gCore.settings.maxOnlinePlayers)) return;
        // copy kick list (less problems)
        List<Player> copiedKickList = Core.gCore.playerData.kickList;
        for (Player p : copiedKickList) {
            // because we are running async we need to tell server thread do kick players
            Bukkit.getScheduler().runTask(Core.getPlugin(Core.class), () -> {
                if (!p.hasPermission("aafk.*") || !p.hasPermission("aafk.kick.bypass")) {
                    p.kickPlayer(Core.gCore.message.getMessage(Message.M.kickReason));
                    Core.gCore.playerData.kickList.remove(p);
                }
            });
        }
    }

}

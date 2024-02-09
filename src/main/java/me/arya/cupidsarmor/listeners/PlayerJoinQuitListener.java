package me.arya.cupidsarmor.listeners;

import me.arya.cupidsarmor.CupidsArmor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class PlayerJoinQuitListener implements Listener {
    private final Set<Player> playerSet = new HashSet<>();
    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if (CupidsArmor.get().getArmorManager().hasEquippedWholeSet(player)) {
            playerSet.add(player);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                boolean hasWholeSet = CupidsArmor.get().getArmorManager().hasEquippedWholeSet(player);

                if (!playerSet.contains(player) && hasWholeSet) {
                    playerSet.add(player);
                    player.sendMessage("§6§lFull set bonus activated!");
                } else if (playerSet.contains(player) && !hasWholeSet) {
                    playerSet.remove(player);
                }
            }
        }.runTaskTimer(CupidsArmor.get(), 10, 10);
    }

    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        playerSet.remove(event.getPlayer());
    }
}
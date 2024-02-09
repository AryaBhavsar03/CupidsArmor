package me.arya.cupidsarmor.listeners;

import me.arya.cupidsarmor.CupidsArmor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerShiftListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlayerShift(final PlayerToggleSneakEvent event) {

        final Player player = event.getPlayer();
        final boolean hasFullSet = CupidsArmor.get().getArmorManager().hasEquippedWholeSet(player);
        if (!hasFullSet) {
            return;
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 300, 0));
    }
}

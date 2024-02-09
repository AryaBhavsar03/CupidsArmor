package me.arya.cupidsarmor.listeners;

import me.arya.cupidsarmor.CupidsArmor;
import net.minelink.ctplus.Tag;
import net.minelink.ctplus.event.PlayerCombatTagEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class CombatTagEventListener implements Listener {
    private final Map<UUID, BukkitTask> tasks;

    public CombatTagEventListener() {
        this.tasks = new HashMap<>();
    }

    @EventHandler(ignoreCancelled = true)
    public void onCombatTag(final PlayerCombatTagEvent event) {
        final Player attacker = event.getAttacker();
        if (!CupidsArmor.get().getArmorManager().hasEquippedWholeSet(attacker)) {
            return;
        }

        final UUID uuid = attacker.getUniqueId();

        if (!CupidsArmor.get().getTagManager().isTagged(uuid)) {
            return;
        }

        Tag tag = CupidsArmor.get().getTagManager().getTag(uuid);
        Player victim = event.getVictim();

        if (tasks.containsKey(attacker.getUniqueId())) {
            return;
        }

        tasks.put(attacker.getUniqueId(),
                new BukkitRunnable() {
                    double oldHealth = victim.getHealth();

                    @Override
                    public void run() {
                        if (tag.isExpired()
                                || !victim.isOnline()) {
                            cancel();
                            tasks.remove(attacker.getUniqueId());
                            return;
                        }

                        double actualHealth = victim.getHealth();

                        if (oldHealth < actualHealth) {
                            if (ThreadLocalRandom.current().nextDouble() < 0.35) {
                                victim.setHealth(oldHealth);
                                double attackersNewHealth = attacker.getHealth() + (actualHealth - oldHealth);
                                if (attackersNewHealth <= 20) {
                                    attacker.setHealth(attackersNewHealth);
                                }
                                attacker.sendMessage("§aEnemy has been “Smooched”");
                            }
                        }

                        oldHealth = actualHealth;
                    }
                }.runTaskTimer(CupidsArmor.get(), 0, 5)
        );
    }
}

package me.arya.cupidsarmor;

import me.arya.cupidsarmor.commands.commands;
import me.arya.cupidsarmor.listeners.CombatTagEventListener;
import me.arya.cupidsarmor.listeners.PlayerJoinQuitListener;
import me.arya.cupidsarmor.listeners.PlayerShiftListener;
import me.arya.cupidsarmor.managers.ArmorManager;
import net.minelink.ctplus.CombatTagPlus;
import net.minelink.ctplus.TagManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CupidsArmor extends JavaPlugin {

    private static CupidsArmor plugin;
    private TagManager tagManager;
    private ArmorManager armorManager;
    @Override
    public void onEnable() {
        setPlugin(this);

        setArmorManager(new ArmorManager());
        CombatTagPlus combatTagPlus = getPlugin(CombatTagPlus.class);
        setTagManager(combatTagPlus.getTagManager());

        registerListeners(
                new CombatTagEventListener(),
                new PlayerShiftListener(),
                new PlayerJoinQuitListener()
        );

        getCommand("cgive").setExecutor(new commands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerListeners(Listener... listeners) {
        final PluginManager pluginManager = getServer().getPluginManager();
        for (Listener listener : listeners) {
            pluginManager.registerEvents(listener, this);
        }
    }

    public static CupidsArmor get() {
        return plugin;
    }

    public static void setPlugin(CupidsArmor plugin) {
        CupidsArmor.plugin = plugin;
    }

    public ArmorManager getArmorManager() {
        return armorManager;
    }

    public void setArmorManager(ArmorManager armorManager) {
        this.armorManager = armorManager;
    }

    public void setTagManager(TagManager tagManager) {
        this.tagManager = tagManager;
    }

    public TagManager getTagManager() {
        return tagManager;
    }
}

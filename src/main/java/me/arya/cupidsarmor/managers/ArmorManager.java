package me.arya.cupidsarmor.managers;

import me.arya.cupidsarmor.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ArmorManager {
    private final ItemStack helmet;

    private final ItemStack chestplate;

    private final ItemStack leggings;

    private final ItemStack boots;

    public ArmorManager() {
        List<String> lore = Arrays.asList(
                "§2==============================================",
                "§2||",
                "§2|| §aCUPID SET",
                "§2||",
                "§2|| §dLove Struck",
                "§2|| §fChance to steal the enemy's hearts when they are",
                "§2|| §fhealing.",
                "§2||",
                "§2||",
                "§2|| §dCupid's Rage",
                "§2|| §fPress §bSHIFT§f to gain strength for 15 seconds.",
                "§2||",
                "§2|| §o§8Full set bonus. Activates when you're wearing the",
                "§2|| §o§8full set.",
                "§2=============================================="
        );

        helmet = ItemBuilder.from(new ItemStack(Material.DIAMOND_HELMET))
                .setName("§dCupid Armor Set")
                .setLore(lore)
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchantment(Enchantment.DURABILITY, 4)
                .build();

        chestplate = ItemBuilder.from(new ItemStack(Material.DIAMOND_CHESTPLATE))
                .setName("§dCupid Armor Set")
                .setLore(lore)
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchantment(Enchantment.DURABILITY, 4)
                .build();

        leggings = ItemBuilder.from(new ItemStack(Material.DIAMOND_LEGGINGS))
                .setName("§dCupid Armor Set")
                .setLore(lore)
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchantment(Enchantment.DURABILITY, 4)
                .build();

        boots = ItemBuilder.from(new ItemStack(Material.DIAMOND_BOOTS))
                .setName("§dCupid Armor Set")
                .setLore(lore)
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchantment(Enchantment.DURABILITY, 4)
                .build();
    }

    public void giveArmor(Player player, String partOfArmor) {
        switch (partOfArmor.toLowerCase(Locale.ENGLISH)) {
            case "leggings":
                player.getInventory().addItem(leggings);
                break;
            case "chestplate":
                player.getInventory().addItem(chestplate);
                break;
            case "helmet":
                player.getInventory().addItem(helmet);
                break;
            case "boots":
                player.getInventory().addItem(boots);
                break;
            default:
                player.sendMessage("§cUnavailable armor, correct values: helmet, chestplate, leggings, boots");
                break;
        }
    }

    public boolean hasEquippedWholeSet(final Player player) {
        for (ItemStack item : player.getInventory().getArmorContents()) {
            if (item == null) return false;
            if (!item.hasItemMeta()) return false;
            if (!item.getItemMeta().getDisplayName().equalsIgnoreCase("§dCupid Armor Set")) return false;
        }
        return true;
    }
}

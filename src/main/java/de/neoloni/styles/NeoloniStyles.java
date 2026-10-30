package de.neoloni.styles;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import java.util.ArrayList;
import java.util.List;

/** Neoloni Styles 2.0: lokale, eingebaute Resourcepacks; kein Netzwerkcode. */
public class NeoloniStyles implements ClientModInitializer {
    public static final String ID = "neolonistyles";
    public record Style(String category, String label, String path) {}
    public static final String[] CATEGORIES = {"Kristalle", "Feuer", "Glowstone", "Stein", "Schwerter", "Äxte", "Spitzhacken", "Schilde", "Totems", "Enderperlen", "Respawn-Anker", "End-Kristalle", "Diamant-Rüstung", "Netherite-Rüstung", "Himmel", "Obsidian", "Wasser", "Lava", "Crosshair", "Hotbar", "Herzen", "Inventar"};
    public static final List<Style> STYLES = List.of(
        new Style("Kristalle", "Eis", "crystal_ice"),
        new Style("Kristalle", "Rosa", "crystal_rose"),
        new Style("Kristalle", "Gold", "crystal_gold"),
        new Style("Feuer", "Blau", "fire_blue"),
        new Style("Feuer", "Grün", "fire_green"),
        new Style("Feuer", "Violett", "fire_violet"),
        new Style("Feuer", "Weiß", "fire_white"),
        new Style("Glowstone", "Pink", "glow_pink"),
        new Style("Glowstone", "Cyan", "glow_cyan"),
        new Style("Glowstone", "Lila", "glow_purple"),
        new Style("Stein", "Eis", "stone_frost"),
        new Style("Stein", "Dunkel", "stone_dark"),
        new Style("Stein", "Rosé", "stone_rose"),
        new Style("Schwerter", "Aqua 1", "v2_sword_01"),
        new Style("Schwerter", "Rubin 2", "v2_sword_02"),
        new Style("Schwerter", "Amethyst 3", "v2_sword_03"),
        new Style("Schwerter", "Smaragd 4", "v2_sword_04"),
        new Style("Schwerter", "Gold 5", "v2_sword_05"),
        new Style("Schwerter", "Frost 6", "v2_sword_06"),
        new Style("Schwerter", "Nacht 7", "v2_sword_07"),
        new Style("Schwerter", "Rosa 8", "v2_sword_08"),
        new Style("Schwerter", "Neon 9", "v2_sword_09"),
        new Style("Schwerter", "Feuer 10", "v2_sword_10"),
        new Style("Äxte", "Aqua 1", "v2_axe_01"),
        new Style("Äxte", "Rubin 2", "v2_axe_02"),
        new Style("Äxte", "Amethyst 3", "v2_axe_03"),
        new Style("Äxte", "Smaragd 4", "v2_axe_04"),
        new Style("Äxte", "Gold 5", "v2_axe_05"),
        new Style("Äxte", "Frost 6", "v2_axe_06"),
        new Style("Spitzhacken", "Aqua 1", "v2_pickaxe_01"),
        new Style("Spitzhacken", "Rubin 2", "v2_pickaxe_02"),
        new Style("Spitzhacken", "Amethyst 3", "v2_pickaxe_03"),
        new Style("Spitzhacken", "Smaragd 4", "v2_pickaxe_04"),
        new Style("Spitzhacken", "Gold 5", "v2_pickaxe_05"),
        new Style("Spitzhacken", "Frost 6", "v2_pickaxe_06"),
        new Style("Schilde", "Aqua 1", "v2_shield_01"),
        new Style("Schilde", "Rubin 2", "v2_shield_02"),
        new Style("Schilde", "Amethyst 3", "v2_shield_03"),
        new Style("Schilde", "Smaragd 4", "v2_shield_04"),
        new Style("Schilde", "Gold 5", "v2_shield_05"),
        new Style("Totems", "Aqua 1", "v2_totem_01"),
        new Style("Totems", "Rubin 2", "v2_totem_02"),
        new Style("Totems", "Amethyst 3", "v2_totem_03"),
        new Style("Totems", "Smaragd 4", "v2_totem_04"),
        new Style("Totems", "Gold 5", "v2_totem_05"),
        new Style("Totems", "Frost 6", "v2_totem_06"),
        new Style("Totems", "Nacht 7", "v2_totem_07"),
        new Style("Totems", "Rosa 8", "v2_totem_08"),
        new Style("Enderperlen", "Aqua 1", "v2_pearl_01"),
        new Style("Enderperlen", "Rubin 2", "v2_pearl_02"),
        new Style("Enderperlen", "Amethyst 3", "v2_pearl_03"),
        new Style("Enderperlen", "Smaragd 4", "v2_pearl_04"),
        new Style("Enderperlen", "Gold 5", "v2_pearl_05"),
        new Style("Respawn-Anker", "Aqua 1", "v2_anchor_01"),
        new Style("Respawn-Anker", "Rubin 2", "v2_anchor_02"),
        new Style("Respawn-Anker", "Amethyst 3", "v2_anchor_03"),
        new Style("Respawn-Anker", "Smaragd 4", "v2_anchor_04"),
        new Style("Respawn-Anker", "Gold 5", "v2_anchor_05"),
        new Style("End-Kristalle", "Aqua 1", "v2_crystal_01"),
        new Style("End-Kristalle", "Rubin 2", "v2_crystal_02"),
        new Style("End-Kristalle", "Amethyst 3", "v2_crystal_03"),
        new Style("End-Kristalle", "Smaragd 4", "v2_crystal_04"),
        new Style("End-Kristalle", "Gold 5", "v2_crystal_05"),
        new Style("End-Kristalle", "Frost 6", "v2_crystal_06"),
        new Style("End-Kristalle", "Nacht 7", "v2_crystal_07"),
        new Style("End-Kristalle", "Rosa 8", "v2_crystal_08"),
        new Style("Diamant-Rüstung", "Aqua 1", "v2_diamondarmor_01"),
        new Style("Diamant-Rüstung", "Rubin 2", "v2_diamondarmor_02"),
        new Style("Diamant-Rüstung", "Amethyst 3", "v2_diamondarmor_03"),
        new Style("Diamant-Rüstung", "Smaragd 4", "v2_diamondarmor_04"),
        new Style("Diamant-Rüstung", "Gold 5", "v2_diamondarmor_05"),
        new Style("Netherite-Rüstung", "Aqua 1", "v2_netheritearmor_01"),
        new Style("Netherite-Rüstung", "Rubin 2", "v2_netheritearmor_02"),
        new Style("Netherite-Rüstung", "Amethyst 3", "v2_netheritearmor_03"),
        new Style("Netherite-Rüstung", "Smaragd 4", "v2_netheritearmor_04"),
        new Style("Netherite-Rüstung", "Gold 5", "v2_netheritearmor_05"),
        new Style("Himmel", "Aqua 1", "v2_sky_01"),
        new Style("Himmel", "Rubin 2", "v2_sky_02"),
        new Style("Himmel", "Amethyst 3", "v2_sky_03"),
        new Style("Himmel", "Smaragd 4", "v2_sky_04"),
        new Style("Himmel", "Gold 5", "v2_sky_05"),
        new Style("Himmel", "Frost 6", "v2_sky_06"),
        new Style("Himmel", "Nacht 7", "v2_sky_07"),
        new Style("Himmel", "Rosa 8", "v2_sky_08"),
        new Style("Himmel", "Neon 9", "v2_sky_09"),
        new Style("Himmel", "Feuer 10", "v2_sky_10"),
        new Style("Himmel", "Ozean 11", "v2_sky_11"),
        new Style("Himmel", "Violett 12", "v2_sky_12"),
        new Style("Himmel", "Silber 13", "v2_sky_13"),
        new Style("Himmel", "Koralle 14", "v2_sky_14"),
        new Style("Himmel", "Obsidian 15", "v2_sky_15"),
        new Style("Obsidian", "Aqua 1", "v2_obsidian_01"),
        new Style("Obsidian", "Rubin 2", "v2_obsidian_02"),
        new Style("Obsidian", "Amethyst 3", "v2_obsidian_03"),
        new Style("Obsidian", "Smaragd 4", "v2_obsidian_04"),
        new Style("Obsidian", "Gold 5", "v2_obsidian_05"),
        new Style("Obsidian", "Frost 6", "v2_obsidian_06"),
        new Style("Obsidian", "Nacht 7", "v2_obsidian_07"),
        new Style("Obsidian", "Rosa 8", "v2_obsidian_08"),
        new Style("Wasser", "Aqua 1", "v2_water_01"),
        new Style("Wasser", "Rubin 2", "v2_water_02"),
        new Style("Wasser", "Amethyst 3", "v2_water_03"),
        new Style("Wasser", "Smaragd 4", "v2_water_04"),
        new Style("Wasser", "Gold 5", "v2_water_05"),
        new Style("Lava", "Aqua 1", "v2_lava_01"),
        new Style("Lava", "Rubin 2", "v2_lava_02"),
        new Style("Lava", "Amethyst 3", "v2_lava_03"),
        new Style("Lava", "Smaragd 4", "v2_lava_04"),
        new Style("Lava", "Gold 5", "v2_lava_05"),
        new Style("Crosshair", "Aqua 1", "v2_crosshair_01"),
        new Style("Crosshair", "Rubin 2", "v2_crosshair_02"),
        new Style("Crosshair", "Amethyst 3", "v2_crosshair_03"),
        new Style("Crosshair", "Smaragd 4", "v2_crosshair_04"),
        new Style("Crosshair", "Gold 5", "v2_crosshair_05"),
        new Style("Crosshair", "Frost 6", "v2_crosshair_06"),
        new Style("Crosshair", "Nacht 7", "v2_crosshair_07"),
        new Style("Crosshair", "Rosa 8", "v2_crosshair_08"),
        new Style("Crosshair", "Neon 9", "v2_crosshair_09"),
        new Style("Crosshair", "Feuer 10", "v2_crosshair_10"),
        new Style("Crosshair", "Ozean 11", "v2_crosshair_11"),
        new Style("Crosshair", "Violett 12", "v2_crosshair_12"),
        new Style("Crosshair", "Silber 13", "v2_crosshair_13"),
        new Style("Crosshair", "Koralle 14", "v2_crosshair_14"),
        new Style("Crosshair", "Obsidian 15", "v2_crosshair_15"),
        new Style("Hotbar", "Aqua 1", "v2_hotbar_01"),
        new Style("Hotbar", "Rubin 2", "v2_hotbar_02"),
        new Style("Hotbar", "Amethyst 3", "v2_hotbar_03"),
        new Style("Hotbar", "Smaragd 4", "v2_hotbar_04"),
        new Style("Hotbar", "Gold 5", "v2_hotbar_05"),
        new Style("Hotbar", "Frost 6", "v2_hotbar_06"),
        new Style("Hotbar", "Nacht 7", "v2_hotbar_07"),
        new Style("Hotbar", "Rosa 8", "v2_hotbar_08"),
        new Style("Herzen", "Aqua 1", "v2_hearts_01"),
        new Style("Herzen", "Rubin 2", "v2_hearts_02"),
        new Style("Herzen", "Amethyst 3", "v2_hearts_03"),
        new Style("Herzen", "Smaragd 4", "v2_hearts_04"),
        new Style("Herzen", "Gold 5", "v2_hearts_05"),
        new Style("Herzen", "Frost 6", "v2_hearts_06"),
        new Style("Herzen", "Nacht 7", "v2_hearts_07"),
        new Style("Herzen", "Rosa 8", "v2_hearts_08"),
        new Style("Inventar", "Aqua 1", "v2_inventory_01"),
        new Style("Inventar", "Rubin 2", "v2_inventory_02"),
        new Style("Inventar", "Amethyst 3", "v2_inventory_03"),
        new Style("Inventar", "Smaragd 4", "v2_inventory_04"),
        new Style("Inventar", "Gold 5", "v2_inventory_05"),
        new Style("Inventar", "Frost 6", "v2_inventory_06"),
        new Style("Inventar", "Nacht 7", "v2_inventory_07"),
        new Style("Inventar", "Rosa 8", "v2_inventory_08")
    );
    private static KeyBinding openMenu;
    @Override public void onInitializeClient() {
        ModContainer container = FabricLoader.getInstance().getModContainer(ID).orElseThrow();
        for (Style s : STYLES) ResourceManagerHelper.registerBuiltinResourcePack(Identifier.of(ID, s.path()), container, Text.literal("Neoloni: " + s.category() + " " + s.label()), ResourcePackActivationType.NORMAL);
        openMenu = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.neolonistyles.menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K, KeyBinding.Category.create(Identifier.of(ID, "main"))));
        ClientTickEvents.END_CLIENT_TICK.register(client -> { while (openMenu.wasPressed()) client.setScreen(new StylesScreen()); });
    }
    static String profileId(ResourcePackManager m, Style s) {
        for (ResourcePackProfile p : m.getProfiles()) { String id=p.getId(); if (id.contains(ID) && id.endsWith(s.path())) return id; }
        for (ResourcePackProfile p : m.getProfiles()) if (p.getId().contains(s.path())) return p.getId();
        return null;
    }
    public static boolean isActive(Style s) { ResourcePackManager m=MinecraftClient.getInstance().getResourcePackManager(); String id=profileId(m,s); return id!=null && m.getEnabledIds().contains(id); }
    public static void select(String category, Style style) {
        MinecraftClient mc=MinecraftClient.getInstance(); ResourcePackManager m=mc.getResourcePackManager(); List<String> enabled=new ArrayList<>(m.getEnabledIds());
        for (Style s:STYLES) if (s.category().equals(category)) { String id=profileId(m,s); if(id!=null) enabled.remove(id); }
        if(style!=null) { String id=profileId(m,style); if(id!=null) enabled.add(id); }
        m.setEnabledProfiles(enabled); mc.options.refreshResourcePacks(m); mc.reloadResources();
    }
}

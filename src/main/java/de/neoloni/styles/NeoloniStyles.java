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

/**
 * Neoloni Styles - rein lokal. Jeder Style ist ein eingebautes Resource Pack im Jar
 * (resourcepacks/<pfad>/). Das Menü schaltet pro Kategorie genau einen Style an.
 * Es gibt keinerlei Netzwerkzugriff.
 */
public class NeoloniStyles implements ClientModInitializer {
    public static final String ID = "neolonistyles";

    public record Style(String category, String label, String path) {}

    public static final String[] CATEGORIES = {"Kristalle", "Feuer", "Glowstone", "Stein"};

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
        new Style("Stein", "Rosé", "stone_rose")
    );

    private static KeyBinding openMenu;

    @Override
    public void onInitializeClient() {
        ModContainer container = FabricLoader.getInstance().getModContainer(ID).orElseThrow();
        for (Style s : STYLES) {
            ResourceManagerHelper.registerBuiltinResourcePack(
                Identifier.of(ID, s.path()), container,
                Text.literal("Neoloni: " + s.category() + " " + s.label()),
                ResourcePackActivationType.NORMAL);
        }

        openMenu = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.neolonistyles.menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K,
            KeyBinding.Category.create(Identifier.of(ID, "main"))));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenu.wasPressed()) client.setScreen(new StylesScreen());
        });
    }

    /** Profil-ID des Packs im ResourcePackManager finden. */
    static String profileId(ResourcePackManager m, Style s) {
        for (ResourcePackProfile p : m.getProfiles()) {
            String id = p.getId();
            if (id.contains(ID) && id.endsWith(s.path())) return id;
        }
        for (ResourcePackProfile p : m.getProfiles()) {
            if (p.getId().contains(s.path())) return p.getId();
        }
        return null;
    }

    public static boolean isActive(Style s) {
        ResourcePackManager m = MinecraftClient.getInstance().getResourcePackManager();
        String id = profileId(m, s);
        return id != null && m.getEnabledIds().contains(id);
    }

    /** style == null -> Kategorie auf Standard zurück. */
    public static void select(String category, Style style) {
        MinecraftClient mc = MinecraftClient.getInstance();
        ResourcePackManager m = mc.getResourcePackManager();
        List<String> enabled = new ArrayList<>(m.getEnabledIds());
        for (Style s : STYLES) {
            if (s.category().equals(category)) {
                String id = profileId(m, s);
                if (id != null) enabled.remove(id);
            }
        }
        if (style != null) {
            String id = profileId(m, style);
            if (id != null) enabled.add(id);   // ans Ende = höchste Priorität
        }
        m.setEnabledProfiles(enabled);
        mc.options.refreshResourcePacks(m);
        mc.reloadResources();
    }
}

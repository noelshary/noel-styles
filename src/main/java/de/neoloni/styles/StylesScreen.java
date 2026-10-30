package de.neoloni.styles;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class StylesScreen extends Screen {
    private static int tab = 0;

    public StylesScreen() { super(Text.literal("Neoloni Styles")); }

    @Override
    protected void init() {
        int left = this.width / 2 - 150;
        int top = this.height / 2 - 80;

        // Kategorien links
        for (int i = 0; i < NeoloniStyles.CATEGORIES.length; i++) {
            final int idx = i;
            Text label = Text.literal(NeoloniStyles.CATEGORIES[i]);
            if (i == tab) label = label.copy().formatted(Formatting.AQUA, Formatting.BOLD);
            this.addDrawableChild(ButtonWidget.builder(label, b -> { tab = idx; this.clearAndInit(); })
                .dimensions(left, top + i * 24, 90, 20).build());
        }

        // Styles rechts
        String cat = NeoloniStyles.CATEGORIES[tab];
        int x = left + 105;
        int y = top;
        boolean anyActive = false;
        for (NeoloniStyles.Style s : NeoloniStyles.STYLES) {
            if (!s.category().equals(cat)) continue;
            boolean active = NeoloniStyles.isActive(s);
            anyActive |= active;
            Text t = active ? Text.literal("▶ " + s.label()).formatted(Formatting.GREEN)
                            : Text.literal(s.label());
            this.addDrawableChild(ButtonWidget.builder(t, b -> {
                NeoloniStyles.select(cat, active ? null : s);
                this.clearAndInit();
            }).dimensions(x, y, 150, 20).build());
            y += 24;
        }
        Text std = !anyActive ? Text.literal("▶ Standard").formatted(Formatting.GREEN) : Text.literal("Standard");
        this.addDrawableChild(ButtonWidget.builder(std, b -> { NeoloniStyles.select(cat, null); this.clearAndInit(); })
            .dimensions(x, y, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Fertig"), b -> this.close())
            .dimensions(this.width / 2 - 50, this.height - 40, 100, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, this.height / 2 - 110, 0xFF9AD0FF);
    }
}

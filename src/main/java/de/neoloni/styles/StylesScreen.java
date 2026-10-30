package de.neoloni.styles;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class StylesScreen extends Screen {
    private static int tab = 0;
    private static int categoryPage = 0;
    private static final int CATEGORIES_PER_PAGE = 6;
    public StylesScreen() { super(Text.literal("Neoloni Styles 2.0")); }
    @Override protected void init() {
        int left=this.width/2-185, top=58;
        int start=categoryPage*CATEGORIES_PER_PAGE;
        for(int i=start;i<Math.min(start+CATEGORIES_PER_PAGE,NeoloniStyles.CATEGORIES.length);i++){
            final int idx=i; Text label=Text.literal(NeoloniStyles.CATEGORIES[i]);
            if(i==tab) label=label.copy().formatted(Formatting.AQUA,Formatting.BOLD);
            this.addDrawableChild(ButtonWidget.builder(label,b->{tab=idx;this.clearAndInit();}).dimensions(left,top+(i-start)*25,112,20).build());
        }
        if(categoryPage>0) this.addDrawableChild(ButtonWidget.builder(Text.literal("◀"),b->{categoryPage--;this.clearAndInit();}).dimensions(left,top+155,52,20).build());
        if((categoryPage+1)*CATEGORIES_PER_PAGE<NeoloniStyles.CATEGORIES.length) this.addDrawableChild(ButtonWidget.builder(Text.literal("▶"),b->{categoryPage++;this.clearAndInit();}).dimensions(left+58,top+155,52,20).build());
        String cat=NeoloniStyles.CATEGORIES[tab]; int x=left+125, y=top; boolean any=false;
        for(NeoloniStyles.Style s:NeoloniStyles.STYLES){ if(!s.category().equals(cat)) continue; boolean active=NeoloniStyles.isActive(s); any|=active;
            Text t=active?Text.literal("✓ "+s.label()).formatted(Formatting.GREEN):Text.literal(s.label());
            this.addDrawableChild(ButtonWidget.builder(t,b->{NeoloniStyles.select(cat,active?null:s);this.clearAndInit();}).dimensions(x,y,205,20).build()); y+=22;
        }
        Text standard=!any?Text.literal("✓ Standard").formatted(Formatting.GREEN):Text.literal("Standard");
        this.addDrawableChild(ButtonWidget.builder(standard,b->{NeoloniStyles.select(cat,null);this.clearAndInit();}).dimensions(x,y,205,20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Fertig"),b->this.close()).dimensions(this.width/2-50,this.height-32,100,20).build());
    }
    @Override public void render(DrawContext context,int mouseX,int mouseY,float delta){
        this.renderBackground(context,mouseX,mouseY,delta); super.render(context,mouseX,mouseY,delta);
        context.drawCenteredTextWithShadow(this.textRenderer,this.title,this.width/2,24,0xFF9AD0FF);
        context.drawTextWithShadow(this.textRenderer,Text.literal("Taste K • lokal/offline • pro Kategorie ein Style"),this.width/2-125,39,0xFFB8B8B8);
    }
}

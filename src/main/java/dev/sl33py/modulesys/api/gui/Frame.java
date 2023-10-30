package dev.sl33py.modulesys.api.gui;

import dev.sl33py.modulesys.ModuleSys;
import dev.sl33py.modulesys.api.gui.components.ModuleButton;

import dev.sl33py.modulesys.api.module.Module;
import dev.sl33py.modulesys.api.wrapper.Wrapper;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

import java.util.ArrayList;

public class Frame implements Wrapper {
    public ArrayList<Component> components;
    private boolean open;
    private final int width;
    private int y;
    private int x;
    private final int barHeight;
    private boolean isDragging;
    public int dragX;
    public int dragY;
    private int height;

    public Frame() {
        this.components = new ArrayList<>();
        this.width = 88;
        this.x = 5;
        this.y = 5;
        this.barHeight = 16;
        this.dragX = 0;
        this.open = true;
        this.isDragging = false;
        int componentY = this.barHeight;
        for (Module module : ModuleSys.getModuleManager().getModules()) {
            ModuleButton moduleButton = new ModuleButton(module, this, componentY);
            components.add(moduleButton);
            componentY += 16;
        }
        update();
    }

    public void renderFrame(DrawContext context) {
        context.fill(x, y, x + width, y + barHeight, ModuleSys.getClickGui().getColor().getRGB());
        context.drawTextWithShadow(mc.textRenderer, "ModuleSys", x + width / 2 - mc.textRenderer.getWidth(Text.of("ModuleSys")) / 2, y + 4, -1);
        System.out.println(components.size());
        if (open && !components.isEmpty()) {
            for (Component component : components) {
                component.render(context);
            }
            context.fill(x, y + height - 1, x + width, y + height, ModuleSys.getClickGui().getColor().getRGB());
        }
    }

    public ArrayList<Component> getComponents() {
        return this.components;
    }

    public void setX(final int newX) {
        this.x = newX;
    }

    public void setY(final int newY) {
        this.y = newY;
    }

    public void setDrag(final boolean drag) {
        this.isDragging = drag;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(final boolean open) {
        this.open = open;
    }

    public void update() {
        int off = this.barHeight;
        for (final Component comp : this.components) {
            comp.setOffset(off);
            off += comp.getHeight();
        }
        this.height = off;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public void updatePosition(final int mouseX, final int mouseY) {
        if (this.isDragging) {
            this.setX(mouseX - this.dragX);
            this.setY(mouseY - this.dragY);
        }
    }

    public boolean isHover(final double x, final double y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.barHeight;
    }

}
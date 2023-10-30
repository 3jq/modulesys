package dev.sl33py.modulesys.api.gui;

import dev.sl33py.modulesys.api.wrapper.Wrapper;
import net.minecraft.client.gui.DrawContext;

public class Component implements Wrapper {

    public void render(DrawContext context) { }

    public void updateComponent(double mouseX, double mouseY) { }

    public void mouseClicked(double mouseX, double mouseY, int button) { }

    public void mouseReleased( double mouseX,  double mouseY,  int mouseButton) { }

    public void keyTyped(int key) { }

    public void setOffset(int offset) { }

    public int getHeight() { return 0; }

}
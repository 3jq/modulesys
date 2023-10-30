package dev.sl33py.modulesys.api.gui;

import dev.sl33py.modulesys.api.wrapper.Wrapper;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class ClickGUI extends Screen implements Wrapper {
    private final Frame frame;
    private Color color;

    public ClickGUI() {
        super(Text.of("modulesys"));
        this.frame = new Frame();
        frame.setX(10);

    }

    @Override
    public boolean shouldPause() { return false; }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float partialTicks) {
        color = new Color(ClickGUIModule.INSTANCE.r.getValue().intValue(), ClickGUIModule.INSTANCE.g.getValue().intValue(), ClickGUIModule.INSTANCE.b.getValue().intValue());
        if (ClickGUIModule.INSTANCE.tint.getValue())
            context.fill(0, 0, mc.getWindow().getScaledWidth(), mc.getWindow().getScaledHeight(), new Color(0, 0, 0, 100).getRGB());
        frame.renderFrame(context);
        frame.updatePosition(mouseX, mouseY);
        frame.getComponents().forEach(c -> c.updateComponent(mouseX, mouseY));
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (frame.isHover(mouseX, mouseY) && mouseButton == 0) {
            frame.setDrag(true);
            frame.dragX = (int) (mouseX - frame.getX());
            frame.dragY = (int) (mouseY - frame.getY());
        }
        if (frame.isHover(mouseX, mouseY) && mouseButton == 1) frame.setOpen(!frame.isOpen());

        if (frame.isOpen() && !frame.getComponents().isEmpty()) {
            for (final Component component : frame.getComponents()) {
                component.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int state) {
        frame.setDrag(false);
        if (frame.isOpen() && !frame.getComponents().isEmpty()) {
            for (final Component component : frame.getComponents()) {
                component.mouseReleased(mouseX, mouseY, state);
            }
        }
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (frame.isOpen() && keyCode != 1 && !frame.getComponents().isEmpty()) {
            for (final Component component : frame.getComponents()) {
                component.keyTyped(keyCode);
            }
        }

        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            mc.setScreen(null);
            ClickGUIModule.INSTANCE.toggle();
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public Frame getFrame() {
        return frame;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
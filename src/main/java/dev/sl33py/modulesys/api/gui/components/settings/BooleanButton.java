package dev.sl33py.modulesys.api.gui.components.settings;

import dev.sl33py.modulesys.api.gui.Component;
import dev.sl33py.modulesys.api.gui.components.ModuleButton;
import dev.sl33py.modulesys.api.setting.Setting;
import dev.sl33py.modulesys.api.wrapper.Wrapper;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class BooleanButton extends Component implements Wrapper {

    private final Setting<Boolean> setting;
    private final ModuleButton button;
    private boolean isHovered;
    private int offset;
    private int x;
    private int y;

    public BooleanButton(final Setting<Boolean> setting, final ModuleButton button, final int offset) {
        this.setting = setting;
        this.button = button;
        this.x = button.frame.getX() + button.frame.getWidth();
        this.y = button.frame.getY() + button.offset;
        this.offset = offset;
    }

    @Override
    public void setOffset(final int offset) {
        this.offset = offset;
    }

    @Override
    public void updateComponent(final double mouseX, final double mouseY) {
        isHovered = isHovered(mouseX, mouseY);
        y = button.frame.getY() + this.offset;
        x = button.frame.getX();
    }

    @Override
    public void mouseClicked(final double mouseX, final double mouseY, final int button) {
        if (isHovered(mouseX, mouseY) && button == 0 && this.button.open) {
            setting.setValue(!setting.getValue());
        }
    }

    @Override
    public void render(DrawContext context) {
        context.fill(button.frame.getX() + 1, button.frame.getY() + offset, button.frame.getX() + button.frame.getWidth() - 1, button.frame.getY() + offset + 16, isHovered ? new Color(0, 0, 0, 75).getRGB() : new Color(0, 0, 0, 60).getRGB());
        context.drawTextWithShadow(mc.textRenderer, setting.getName(), button.frame.getX() + 5, button.frame.getY() + offset + 3, setting.getValue() ? -1 : new Color(170, 170, 170).getRGB());
    }

    public boolean isHovered(final double x, final double y) {
        return x > this.x && x < this.x + 88 && y > this.y && y < this.y + 16;
    }

}
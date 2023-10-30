package dev.sl33py.modulesys.api.gui.components;

import dev.sl33py.modulesys.ModuleSys;
import dev.sl33py.modulesys.api.gui.Frame;
import dev.sl33py.modulesys.api.gui.components.settings.ModeButton;
import dev.sl33py.modulesys.api.gui.components.settings.SliderButton;
import dev.sl33py.modulesys.api.module.Module;
import dev.sl33py.modulesys.api.gui.components.settings.BooleanButton;
import dev.sl33py.modulesys.api.gui.Component;
import dev.sl33py.modulesys.api.gui.components.settings.KeyButton;
import dev.sl33py.modulesys.api.setting.Setting;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.ArrayList;

public class ModuleButton extends Component {

    public Module module;
    public Frame frame;
    public int offset;
    private boolean isHovered;
    private final ArrayList<Component> components;
    public boolean open;
    private final int height;

    public ModuleButton(Module module, Frame frame, int offset) {
        this.module = module;
        this.frame = frame;
        this.offset = offset;
        this.components = new ArrayList<>();
        this.open = false;
        this.height = 16;
        int settingY = this.offset + 16;
        if (!ModuleSys.getSettingManager().getSettings(module).isEmpty()) {
            for (Setting s : ModuleSys.getSettingManager().getSettings(module)) {
                switch (s.getType()) {
                    case B:
                        components.add(new BooleanButton(s, this, settingY));
                        continue;
                    case N:
                        components.add(new SliderButton(s, this, settingY));
                        continue;
                    case M:
                        components.add(new ModeButton(s, this, settingY));
                        continue;
                    default:
                        settingY += 16;
                        break;
                }
            }
        }
        components.add(new KeyButton(this, settingY));
    }

    @Override
    public void setOffset(final int offset) {
        this.offset = offset;
        int settingY = this.offset + 16;
        for (Component c : components) {
            c.setOffset(settingY);
            settingY += 16;
        }
    }

    @Override
    public int getHeight() {
        if (open) return 16 * (components.size() + 1);
        return 16;
    }

    @Override
    public void updateComponent(final double mouseX, final double mouseY) {
        isHovered = isHovered(mouseX, mouseY);
        if (!components.isEmpty()) {
            components.forEach(c -> {
                c.updateComponent(mouseX, mouseY);
            });
        }
    }

    @Override
    public void mouseClicked(final double mouseX, final double mouseY, final int button) {
        if (isHovered(mouseX, mouseY) && button == 0) {
            module.toggle();
        }

        if (isHovered(mouseX, mouseY) && button == 1) {
            open = !open;
            frame.update();
        }

        components.forEach(c -> {
            c.mouseClicked(mouseX, mouseY, button);
        });
    }

    @Override
    public void mouseReleased(final double mouseX, final double mouseY, final int mouseButton) {
        components.forEach(c -> {
            c.mouseReleased(mouseX, mouseY, mouseButton);
        });
    }

    @Override
    public void keyTyped(final int key) {
        components.forEach(c -> {
            c.keyTyped(key);
        });
    }

    @Override
    public void render(DrawContext context) {
        context.fill(frame.getX(), frame.getY() + offset, frame.getX() + frame.getWidth(), frame.getY() + offset + 16, isHovered ? new Color(0, 0, 0, 75).getRGB() : new Color(0, 0, 0, 60).getRGB());
        context.fill(frame.getX(), frame.getY() + offset, frame.getX() + 1, frame.getY() + offset + getHeight(), ModuleSys.getClickGui().getColor().getRGB());
        context.fill(frame.getX() + frame.getWidth(), frame.getY() + offset, frame.getX() + frame.getWidth() - 1, frame.getY() + offset + getHeight(), ModuleSys.getClickGui().getColor().getRGB());
        context.drawTextWithShadow(mc.textRenderer, module.getName(), frame.getX() + 3, frame.getY() + offset + 3, module.isEnabled() ? -1 : new Color(170, 170, 170).getRGB());
        if (open) components.forEach(component -> component.render(context));
    }

    public boolean isHovered(final double x, final double y) {
        return x > this.frame.getX() && x < this.frame.getX() + this.frame.getWidth() && y > this.frame.getY() + this.offset && y < this.frame.getY() + 16 + this.offset;
    }
}
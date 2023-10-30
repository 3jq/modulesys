package dev.sl33py.modulesys.api.gui;

import dev.sl33py.modulesys.ModuleSys;
import dev.sl33py.modulesys.api.module.Module;
import dev.sl33py.modulesys.api.setting.Setting;
import dev.sl33py.modulesys.api.wrapper.Wrapper;
import org.lwjgl.glfw.GLFW;

public class ClickGUIModule extends Module implements Wrapper {
    public static ClickGUIModule INSTANCE;
    public Setting<Double> r = register("Red", 130, 0, 255, 0);
    public Setting<Double> g = register("Green", 125, 0, 255, 0);
    public Setting<Double> b = register("Blue", 200, 0, 255, 0);
    public Setting<Boolean> tint = register("Tint", true);

    public ClickGUIModule() {
        super("GUI", "Click GUI (the GUI u're in rn)");
        setKey(GLFW.GLFW_KEY_RIGHT_CONTROL);
        INSTANCE = this;
    }

    @Override public void onEnable() {
        if (ModuleSys.getClickGui() == null) ModuleSys.setClickGui(new ClickGUI());
        if (mc.player != null) mc.setScreen(ModuleSys.getClickGui());
    }

    @Override public void onDisable() {
        if (mc.player != null) mc.setScreen(null);
    }
}

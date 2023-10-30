package dev.sl33py.modulesys.api.module;

import dev.sl33py.modulesys.ModuleSys;
import dev.sl33py.modulesys.api.setting.Setting;

import java.util.List;

public class Module {

    private String name;
    private String description;
    private int key = -1;
    private boolean drawn = true;

    private boolean enabled = false;

    public Module(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setDrawn(boolean drawn) { this.drawn = drawn; }

    public boolean isDrawn() { return drawn; }

    public void toggle() {
        if (isEnabled()) disable();
        else enable();
    }

    public void enable() {
        setEnabled(true);
        onEnable();
    }

    public void disable() {
        setEnabled(false);
        onDisable();
    }

    public void onUpdate() {}

    public void onEnable() {}

    public void onDisable() {}

    public Setting<Boolean> register(String name, boolean value) {
        Setting<Boolean> s = new Setting<>(this, name, value);
        ModuleSys.getSettingManager().register(s);
        return s;
    }

    public Setting<Double> register(String name, double value, double min, double max, double inc) {
        Setting<Double> s = new Setting<>(this, name, value, min, max, inc);
        ModuleSys.getSettingManager().register(s);
        return s;
    }

    public Setting<String> register(String name, String value, List<String> modes) {
        Setting<String> s = new Setting<>(this, name, value, modes);
        ModuleSys.getSettingManager().register(s);
        return s;
    }
}
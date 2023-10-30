package dev.sl33py.modulesys.api.module;

import java.util.*;

public class ModuleManager {

    private final List<Module> modules;

    public ModuleManager() {
        modules = new ArrayList<>();
    }

    public Module getModule(String name) {
        return modules.stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public List<Module> getModules() {
        return modules;
    }

    public void addModule(Module module) {
        modules.add(module);
    }
}
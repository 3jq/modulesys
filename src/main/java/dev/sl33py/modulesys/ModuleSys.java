package dev.sl33py.modulesys;

import dev.sl33py.modulesys.api.gui.ClickGUI;
import dev.sl33py.modulesys.api.gui.ClickGUIModule;
import dev.sl33py.modulesys.api.module.ModuleManager;
import dev.sl33py.modulesys.api.setting.SettingManager;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class ModuleSys implements ModInitializer {
    private static Logger logger = (Logger) LogManager.getLogger("modulesys");
    private static SettingManager settingManager;
    private static ModuleManager moduleManager;
    private static ClickGUI clickGui;

    @Override
    public void onInitialize() {
        logger.info("ModuleSys is initializing...");
        settingManager = new SettingManager();
        moduleManager = new ModuleManager();
        moduleManager.addModule(new ClickGUIModule());
    }

    public static SettingManager getSettingManager() {
        return settingManager;
    }

    public static ModuleManager getModuleManager() {
        return moduleManager;
    }

    public static ClickGUI getClickGui() {
        return clickGui;
    }

    public static void setClickGui(ClickGUI clickGui) {
        ModuleSys.clickGui = clickGui;
    }

    public static Logger getLogger() {
        return logger;
    }
}

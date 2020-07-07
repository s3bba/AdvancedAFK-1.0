package me.sebbaindustries.advancedafk;

import me.sebbaindustries.advancedafk.global.GlobalCore;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * <b>Copyright 2020 SebbaIndustries</b> <br>
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); <br>
 * you may not use this file except in compliance with the License. <br>
 * You may obtain a copy of the License at <br>
 * <br>
 * <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a> <br>
 * <br>
 * Unless required by applicable law or agreed to in writing, software <br>
 * distributed under the License is distributed on an <b>"AS IS" BASIS, <br>
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND</b>, either express or implied. <br>
 * See the License for the specific language governing permissions and <br>
 * limitations under the License. <br>
 *
 * @author sebbaindustries
 * @version 1.0
 * @see org.bukkit.plugin.Plugin As plugin interface
 */
public final class Core extends JavaPlugin {

    /**
     * @see GlobalCore
     * Global core instance for access to everything
     */
    public static GlobalCore gCore;

    /**
     * @see org.bukkit
     * Activates when plugin is loaded
     */
    @Override
    public void onLoad() {
        super.onLoad();
    }

    /**
     * @see org.bukkit
     * Activates when plugin is disabled
     */
    @Override
    public void onDisable() {
        super.onDisable();
    }

    /**
     * @see org.bukkit
     * Activates when plugin is Enabled
     */
    @Override
    public void onEnable() {
        super.onEnable();
        setup();
    }

    /**
     * @see GlobalCore
     * Initializes gCore variable with new instance of GlobalCore class
     */
    private void setup() {
        gCore = new GlobalCore(this);
        gCore.debug.useDebug = gCore.fileManager.getConfiguration().getBoolean("console.debug");
        gCore.lang.LANG = gCore.fileManager.getConfiguration().getString("language");
        gCore.settings.reloadSettings();
        gCore.message.reloadMessages();
    }

}

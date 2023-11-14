package dice.jiuc.dicyrps;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class DataBase {
    private final File file;
    private final FileConfiguration config;

    public DataBase(String name, Plugin plugin) {
        plugin.saveDefaultConfig();

        file = new File(plugin.getDataFolder(), name);

        try {
            if (!file.exists() && !file.createNewFile())
                throw new IOException();

        } catch (IOException e) {
            throw new RuntimeException("Не получилось создать файл", e);
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException("Не получилось сохранить файл", e);
        }
    }

    // Write and save changes
    public void write(String path, Object object) {
        getConfig().set(path, object);
        save();
    }
}
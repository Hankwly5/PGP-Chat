package io.github.hankwly5.pgpchat.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

public class config {
    public static List<String> loadConfigKey(String key) throws IOException {
        Path config = net.fabricmc.loader.api.FabricLoader.getInstance()
                .getConfigDir()
                .resolve("PGP-Chat/config.txt");

        Files.createDirectories(config.getParent());
        if (!Files.exists(config)) {
            Files.writeString(config, "# The fingerprint, email, or key id of your message's recipient's pgp key (must be in gpg keyring), you can add more recipient: lines to add more recipients\nrecipient: \n# The path to gpg, you probably don't need to put anything here if your gpg is at a standard path\ngpg-path: ");
        }

        List<String> values = new ArrayList<>();
        String prefix = key + ":";
        for (String line : Files.readAllLines(config)) {
            if (line.startsWith(prefix)) {
                String v = line.substring(prefix.length()).trim();
                if (!v.isEmpty()) values.add(v);
            }
        }
        return values.isEmpty() ? null : values;
    }
}

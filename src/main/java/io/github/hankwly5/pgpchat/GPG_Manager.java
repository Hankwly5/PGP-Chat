package io.github.hankwly5.pgpchat;

import java.io.File;

public class GPG_Manager {

    public static String GPG_Path() {
        String[] possible_paths = {
            "/usr/bin/gpg2",
            "/usr/local/bin/gpg2",
            "/opt/homebrew/bin/gpg2",
            "C:/Program Files (x86)/GnuPG/bin/gpg.exe",
            "/usr/bin/gpg",
            "/usr/local/bin/gpg",
            "/opt/homebrew/bin/gpg"
        };

        for (String path : possible_paths) {
            if (new File(path).exists()) {
                return path;
            }
        }

        return null;
    }
}

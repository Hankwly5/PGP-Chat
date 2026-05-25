package io.github.hankwly5.pgpchat;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import io.github.hankwly5.pgpchat.config.config;

public class Decryption {
    public static String decrypt(List<String> messages, String sender) {
    StringBuilder sb = new StringBuilder("");
    for (String s : messages) {
        int index = s.indexOf("pgp:");

        if (index != -1) {
            String result = s.substring(s.lastIndexOf(':') + 1);
            sb.append(result);
        }
        
    }
    int index = sb.indexOf("-----BEGIN PGP MESSAGE-----");
    if (index != -1) {
        sb.insert(index + "-----BEGIN PGP MESSAGE-----".length(), "\n\n");
    }
    index = sb.indexOf("-----END PGP MESSAGE-----");
    if (index != -1) {
        sb.insert(index, '\n');
    }
    try {
	List<String> gpg_path = config.loadConfigKey("gpg-path");
	if (gpg_path == null) gpg_path = new ArrayList<>();
	if (gpg_path.isEmpty()) gpg_path.add("gpg");
        Process process = Runtime.getRuntime().exec(new String[]{gpg_path.get(0), "--no-tty", "--decrypt"});
        process.getOutputStream().write(sb.toString().getBytes());
        process.getOutputStream().close();

        final StringBuilder errSb = new StringBuilder();
        Thread errThread = new Thread(() -> {
            try { errSb.append(new String(process.getErrorStream().readAllBytes())); }
            catch (IOException e) { e.printStackTrace(); }
        });

        errThread.start();
        String decrypted = new String(process.getInputStream().readAllBytes());
        try { errThread.join(); } catch (InterruptedException e) { e.printStackTrace(); }
        return sender + " " + decrypted;
    } catch (IOException e) {
        e.printStackTrace();
    }
    return "Decryption Failed";
    }
}

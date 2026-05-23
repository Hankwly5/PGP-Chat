package io.github.hankwly5.pgpchat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import io.github.hankwly5.pgpchat.config.config;

public class Encryption {

    public record GpgKey(String fingerprint, String uid) {}

    public static String encrypt(String message) throws IOException {
        if (!message.startsWith("enc:")) return message;
        String plaintext = message.substring(4);

        List<String> recipients = config.loadConfigKey("recipient");
        if (recipients == null || recipients.isEmpty()) throw new IOException("No key configured");

        List<String> cmd = new ArrayList<>(List.of("gpg", "--encrypt", "--armor", "--batch"));
        for (String r : recipients) {
            cmd.add("--recipient");
            cmd.add(r);
        }

        Process p = Runtime.getRuntime().exec(cmd.toArray(new String[0]));

        p.getOutputStream().write(plaintext.getBytes());
        p.getOutputStream().close();

        String ciphertext = new String(p.getInputStream().readAllBytes());
        ciphertext = ciphertext.replaceAll("\n", "");
        return ciphertext;
    }

    public static List<String> encryptSplit(String message) throws IOException {
        if (!message.startsWith("enc:")) return List.of(message);
        
        String ciphertext = encrypt(message);
        
        // split into 200 char chunks (leaving room for prefix)
        List<String> chunks = new ArrayList<>();
        String id = String.valueOf(System.currentTimeMillis()).substring(8); // short unique id
        int total = (int) Math.ceil(ciphertext.length() / 200.0);
        
        for (int i = 0; i < total; i++) {
            int start = i * 200;
            int end = Math.min(start + 200, ciphertext.length());
            // format: pgp:ID:PART/TOTAL:DATA
            chunks.add("pgp:" + id + ":" + (i+1) + "/" + total + ":" + ciphertext.substring(start, end));
        }
        return chunks;
    }
}
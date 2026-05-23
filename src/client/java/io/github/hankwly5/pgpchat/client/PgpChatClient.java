package io.github.hankwly5.pgpchat.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import io.github.hankwly5.pgpchat.Decryption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PgpChatClient implements ClientModInitializer {
    private static final List<String> messages = new ArrayList<>();

    @Override
    public void onInitializeClient() {
        ClientReceiveMessageEvents.ALLOW_CHAT.register((message, signedMessage, sender, params, receptionTime) -> {
            String raw = message.getString();
            String senderName = "";
            Pattern p = Pattern.compile("<[^>]+>");
            Matcher m = p.matcher(raw);
            if (m.find()) senderName = m.group();

            int pgpIndex = raw.indexOf("pgp:");
            if (pgpIndex == -1) return true;
            raw = raw.substring(pgpIndex);
            messages.add(raw);
            String[] parts = raw.split(":");
            int part = Integer.parseInt(parts[2].split("/")[0]);
            int total = Integer.parseInt(parts[2].split("/")[1]);
            if (part == total) {
                String decrypted = Decryption.decrypt(messages, senderName);
                messages.clear();
                Minecraft.getInstance().gui.getChat()
                    .addClientSystemMessage(Component.literal(decrypted));
            }
            return false;
        });
    }
}

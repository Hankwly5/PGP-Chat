package io.github.hankwly5.pgpchat.client.mixin;

import net.minecraft.client.multiplayer.ClientPacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Hud;
import net.minecraft.network.chat.Component;
import io.github.hankwly5.pgpchat.Encryption;
import java.io.IOException;
import java.util.List;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ChatMixin {
    @Inject(method = "sendChat", at = @At("HEAD"), cancellable = true)
    private void modifyChat(String message, CallbackInfo ci) {
        if (!message.startsWith("enc:")) return;
        try {
            List<String> chunks = Encryption.encryptSplit(message);
            ClientPacketListener self = (ClientPacketListener)(Object)this;
            for (String chunk : chunks) {
                self.sendChat(chunk); // send each chunk
            }
            ci.cancel(); // cancel the original message
            } catch (IOException e) {
            if (e.getMessage() != null && e.getMessage().equals("No key configured")) {
                Hud hud = ((GuiAccessor) Minecraft.getInstance().gui).getHud();
                hud.getChat().addClientSystemMessage(Component.literal("PGP-Chat: No recipient key configured. Edit config/PGP-Chat/config.txt"));
            } else {
                e.printStackTrace();
            }
            ci.cancel();
        }
    }
}

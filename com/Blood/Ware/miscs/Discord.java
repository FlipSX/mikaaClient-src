package com.Blood.Ware.miscs;

import com.Blood.Ware.miscs.discordhelper.DiscordEventHandlers;
import com.Blood.Ware.miscs.discordhelper.DiscordRPC;
import com.Blood.Ware.miscs.discordhelper.DiscordRichPresence;

public class Discord {
   public static String discordID = "1297991320291381341";
   public static DiscordRichPresence discordRichP = new DiscordRichPresence();
   public static DiscordRPC discordRPC;

   public static void startRPC() {
      DiscordEventHandlers eventHandlers = new DiscordEventHandlers();
      eventHandlers.disconnected = (var1, var2) -> {
         System.out.println("Discord RPC disconnected, var1:" + var1 + ", var2: " + var2);
      };
      discordRPC.Discord_Initialize(discordID, eventHandlers, true, (String)null);
      discordRichP.startTimestamp = System.currentTimeMillis() / 1000L;
      discordRichP.details = "Jeklout<3";
      discordRichP.largeImageKey = "1";
      discordRichP.largeImageText = "MikaaClient";
      discordRichP.state = null;
      discordRPC.Discord_UpdatePresence(discordRichP);
   }

   public static void stopRPC() {
      discordRPC.Discord_Shutdown();
      discordRPC.Discord_ClearPresence();
   }

   static {
      discordRPC = DiscordRPC.INSTANCE;
   }
}

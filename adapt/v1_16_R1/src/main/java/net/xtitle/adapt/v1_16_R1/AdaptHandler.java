package net.xtitle.adapt.v1_16_R1;

import net.minecraft.server.v1_16_R1.IChatBaseComponent;
import net.minecraft.server.v1_16_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_16_R1.PlayerConnection;
import net.xtitle.api.adapt.SimpleAdapt;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class AdaptHandler
implements SimpleAdapt {
	public AdaptHandler() {}
	
	/**
	 * Send a title with times settings.
	 *
	 * @param player   Player object.
	 * @param title    Title message.
	 * @param subtitle Subtitle message.
	 * @param fadeIn   In-coming time.
	 * @param stay     Staying time.
	 * @param fadeOut  Outing time.
	 */
	@Override
	public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		
		connection.sendPacket(new PacketPlayOutTitle(fadeIn, stay, fadeOut));
		connection.sendPacket(new PacketPlayOutTitle(
			 PacketPlayOutTitle.EnumTitleAction.TITLE,
			 IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}"))
		);
		connection.sendPacket(new PacketPlayOutTitle(
			 PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
			 IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}"))
		);
	}
	
	/**
	 * Send an actionbar to player.
	 *
	 * @param player  Player object.
	 * @param message Message to send.
	 */
	@Override
	public void sendActionBar(Player player, String message) {
		((CraftPlayer) player).getHandle()
			 .playerConnection
			 .sendPacket(new PacketPlayOutTitle(
				  PacketPlayOutTitle.EnumTitleAction.ACTIONBAR,
				  IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}"))
			 );
	}
}
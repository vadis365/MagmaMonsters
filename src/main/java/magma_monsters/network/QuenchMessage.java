package magma_monsters.network;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class QuenchMessage {

	public static float posX;
	public static float posY;
	public static float posZ;
	public static byte type;

	public QuenchMessage(float x, float y, float z, byte particleType) {
		posX = x;
		posY = y;
		posZ = z;
		type = particleType;
	}

	public static void encode(QuenchMessage pkt, FriendlyByteBuf buf) {
		buf.writeFloat(posX);
		buf.writeFloat(posY);
		buf.writeFloat(posZ);
		buf.writeByte(type);
	}

	public QuenchMessage (FriendlyByteBuf buf) {
		posX = buf.readFloat();
		posY = buf.readFloat();
		posZ = buf.readFloat();
		type = buf.readByte();
	}

	public static class Handler {
		@SuppressWarnings("static-access")
		public static void handle(final QuenchMessage pkt, Supplier<NetworkEvent.Context> ctx) {
		      DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> QuenchMessageClient.handlePacket(msg, ctx)));
		      ctx.get().setPacketHandled(true);

		}
	}
}

package magma_monsters.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;

public class QuenchMessage {
	
	public static void handle(final QuenchMessageClient pkt, final IPayloadContext context) {
		context.enqueueWork(() -> {
			QuenchMessageClientParticles.handlePacket(pkt.posX(), pkt.posY(), pkt.posZ(), pkt.effect());
		});
	}
/*
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
		public static void handle(final QuenchMessage pkt, Supplier<NetworkEvent.Context> ctx) {
			if(ctx.get().getDirection().getReceptionSide().isClient()) 
		      QuenchMessageClient.handlePacket(pkt, ctx);
			ctx.get().setPacketHandled(true);
		}
	}
	*/
}

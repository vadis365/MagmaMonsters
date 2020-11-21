package magma_monsters.network;

import java.util.function.Supplier;

import magma_monsters.particles.ClientParticles;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.network.NetworkEvent;

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

	public static void encode(QuenchMessage pkt, PacketBuffer buf) {
		buf.writeFloat(posX);
		buf.writeFloat(posY);
		buf.writeFloat(posZ);
		buf.writeByte(type);
	}

	public QuenchMessage (PacketBuffer buf) {
		posX = buf.readFloat();
		posY = buf.readFloat();
		posZ = buf.readFloat();
		type = buf.readByte();
	}

	public static class Handler {
		@SuppressWarnings("static-access")
		public static void handle(final QuenchMessage pkt, Supplier<NetworkEvent.Context> ctx) {
			if(ctx.get().getDirection().getReceptionSide().isClient()) {
				ctx.get().enqueueWork(() -> {
					for (int a = 0; a < 360; a += 10) {
						double ang = a * Math.PI / 180D;
						if (pkt.type == 0)
							ClientParticles.spawnCustomParticle("smoke", pkt.posX + -MathHelper.sin((float) ang) * 0.125F, pkt.posY, pkt.posZ + MathHelper.cos((float) ang) * 0.125F, -MathHelper.sin((float) ang) * 0.1, 0.05D, MathHelper.cos((float) ang) * 0.1);
						if (pkt.type == 1)
							ClientParticles.spawnCustomParticle("flame", pkt.posX + -MathHelper.sin((float) ang) * 0.125F, pkt.posY, pkt.posZ + MathHelper.cos((float) ang) * 0.125F, -MathHelper.sin((float) ang) * 0.1, 0.05D, MathHelper.cos((float) ang) * 0.1);
					}
				});
			}
			ctx.get().setPacketHandled(true);
		}	
	}
}

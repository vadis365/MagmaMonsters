package magma_monsters.network;

import java.util.function.Supplier;

import magma_monsters.particles.ClientParticles;
import net.minecraft.util.Mth;
import net.minecraftforge.network.NetworkEvent;

public class QuenchMessageClient {

	@SuppressWarnings("static-access")
	public static void handlePacket(QuenchMessage pkt, Supplier<NetworkEvent.Context> ctx) {
				ctx.get().enqueueWork(() -> {
					for (int a = 0; a < 360; a += 10) {
						double ang = a * Math.PI / 180D;
						if (pkt.type == 0)
							ClientParticles.spawnCustomParticle("smoke", pkt.posX + -Mth.sin((float) ang) * 0.125F, pkt.posY, pkt.posZ + Mth.cos((float) ang) * 0.125F, -Mth.sin((float) ang) * 0.1, 0.05D, Mth.cos((float) ang) * 0.1);
						if (pkt.type == 1)
							ClientParticles.spawnCustomParticle("flame", pkt.posX + -Mth.sin((float) ang) * 0.125F, pkt.posY, pkt.posZ + Mth.cos((float) ang) * 0.125F, -Mth.sin((float) ang) * 0.1, 0.05D, Mth.cos((float) ang) * 0.1);
					}
				});
			ctx.get().setPacketHandled(true);
		}
}

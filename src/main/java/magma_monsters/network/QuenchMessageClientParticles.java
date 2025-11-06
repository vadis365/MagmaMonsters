package magma_monsters.network;

import magma_monsters.particles.ClientParticles;
import net.minecraft.util.Mth;

public class QuenchMessageClientParticles {
	public static void handlePacket(double x, double y, double z, byte effect) {
		for (int a = 0; a < 360; a += 10) {
			double ang = a * Math.PI / 180D;
			ClientParticles.spawnCustomParticle(effect == 0 ? "smoke" : "flame", x + -Mth.sin((float) ang) * 0.125F, y, z + Mth.cos((float) ang) * 0.125F,  -Mth.sin((float) ang) * 0.5, 0D, Mth.cos((float) ang) * 0.5);
			ClientParticles.spawnCustomParticle(effect == 0 ? "smoke" : "flame", x + -Mth.sin((float) ang) * 0.125F, y + Mth.cos((float) ang) * 0.125F, z, -Mth.sin((float) ang) * 0.2, Mth.cos((float) ang) * 0.2, 0D);
			ClientParticles.spawnCustomParticle(effect == 0 ? "smoke" : "flame", x, y + -Mth.sin((float) ang) * 0.125F, z + Mth.cos((float) ang) * 0.125F, 0D, -Mth.sin((float) ang) * 0.2, Mth.cos((float) ang) * 0.2);
		}
	}
}

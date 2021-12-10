package magma_monsters.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class ClientParticles {

	@SuppressWarnings("resource")
	public static void spawnCustomParticle(String particleName, double x, double y, double z, double vecX, double vecY, double vecZ) {
		Level level = Minecraft.getInstance().level;
		if (particleName.equals("lava"))
			level.addParticle(ParticleTypes.LAVA, false, x, y, z, vecX, vecY, vecZ);

		if (particleName.equals("smoke"))
			level.addParticle(ParticleTypes.LARGE_SMOKE, false, x, y, z, vecX, vecY, vecZ);

		if (particleName.equals("flame"))
			level.addParticle(ParticleTypes.FLAME, false, x, y, z, vecX, vecY, vecZ);
	}
}

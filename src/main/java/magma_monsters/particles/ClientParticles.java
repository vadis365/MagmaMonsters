package magma_monsters.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class ClientParticles {

	public static void spawnCustomParticle(String particleName, double x, double y, double z, double vecX, double vecY, double vecZ) {
		World world = Minecraft.getInstance().world;
		if (particleName.equals("lava"))
			world.addParticle(ParticleTypes.LAVA, false, x, y, z, vecX, vecY, vecZ);

		if (particleName.equals("smoke"))
			world.addParticle(ParticleTypes.LARGE_SMOKE, false, x, y, z, vecX, vecY, vecZ);

		if (particleName.equals("flame"))
			world.addParticle(ParticleTypes.FLAME, false, x, y, z, vecX, vecY, vecZ);
	}
}

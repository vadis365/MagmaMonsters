package magma_monsters.particles;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class ClientParticles {

	public static void spawnCustomParticle(String particleName, World world, double x, double y, double z, double vecX, double vecY, double vecZ) {

		if (particleName.equals("lava"))
			world.addParticle(ParticleTypes.LAVA, x, y, z, vecX, vecY, vecZ);

		if (particleName.equals("smoke"))
			world.addParticle(ParticleTypes.LARGE_SMOKE, x, y, z, vecX, vecY, vecZ);

		if (particleName.equals("flame"))
			world.addParticle(ParticleTypes.FLAME, x, y, z, vecX, vecY, vecZ);
	}

}

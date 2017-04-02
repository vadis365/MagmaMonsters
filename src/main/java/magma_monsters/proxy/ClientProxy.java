package magma_monsters.proxy;

import magma_monsters.client.render.RenderMagmaMonster;
import magma_monsters.client.render.RenderMagmaMonsterGrunt;
import magma_monsters.entities.EntityMagmaMonster;
import magma_monsters.entities.EntityMagmaMonsterGrunt;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityMagmaMonster.class, RenderMagmaMonster::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityMagmaMonsterGrunt.class, RenderMagmaMonsterGrunt::new);
	}

	@Override
	public void spawnCustomParticle(String particleName, World world, double x, double y, double z, double vecX, double vecY, double vecZ) {

		if (particleName.equals("lava"))
			world.spawnParticle(EnumParticleTypes.LAVA, x, y, z, vecX, vecY, vecZ, new int[0]);

		if (particleName.equals("smoke"))
			world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, x, y, z, vecX, vecY, vecZ, new int[0]);

		if (particleName.equals("flame"))
			world.spawnParticle(EnumParticleTypes.FLAME, x, y, z, vecX, vecY, vecZ, new int[0]);
	}

}

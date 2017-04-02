package magma_monsters.network;

import magma_monsters.MagmaMonsters;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class QuenchPacketHandler implements IMessageHandler<QuenchMessage, IMessage> {

	@Override
	@SideOnly(Side.CLIENT)
	public IMessage onMessage(QuenchMessage message, MessageContext ctx) {
		World world = FMLClientHandler.instance().getWorldClient();
		if (world == null)
			return null;
		else if (world.isRemote)
			for (int a = 0; a < 360; a += 10) {
				double ang = a * Math.PI / 180D;
				if(message.type == 0)
					MagmaMonsters.PROXY.spawnCustomParticle("smoke", world, message.posX + -MathHelper.sin((float) ang) * 0.125F, message.posY, message.posZ + MathHelper.cos((float) ang) * 0.125F,  -MathHelper.sin((float) ang) * 0.1, 0.05D, MathHelper.cos((float) ang) * 0.1);
				if(message.type == 1)
					MagmaMonsters.PROXY.spawnCustomParticle("flame", world, message.posX + -MathHelper.sin((float) ang) * 0.125F, message.posY, message.posZ + MathHelper.cos((float) ang) * 0.125F,  -MathHelper.sin((float) ang) * 0.1, 0.05D, MathHelper.cos((float) ang) * 0.1);
			}
		return null;
	}
}
package magma_monsters.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class QuenchMessage implements IMessage {

	public float posX, posY, posZ;
	public byte type;
	public QuenchMessage() {
	}

	public QuenchMessage(float x, float y, float z, byte particleType) {
		posX = x;
		posY = y;
		posZ = z;
		type = particleType;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeFloat(posX);
		buf.writeFloat(posY);
		buf.writeFloat(posZ);
		buf.writeByte(type);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		posX = buf.readFloat();
		posY = buf.readFloat();
		posZ = buf.readFloat();
		type = buf.readByte();
	}

}

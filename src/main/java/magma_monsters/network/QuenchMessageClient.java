package magma_monsters.network;

import io.netty.buffer.ByteBuf;
import magma_monsters.MagmaMonsters;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record QuenchMessageClient (float posX, float posY, float posZ, byte effect) implements CustomPacketPayload {
	
	public static final CustomPacketPayload.Type<QuenchMessageClient> TYPE = new CustomPacketPayload.Type<>(MagmaMonsters.prefix("disruptor_particle"));

    public static final StreamCodec<ByteBuf, QuenchMessageClient> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT,
            QuenchMessageClient::posX,
            ByteBufCodecs.FLOAT,
            QuenchMessageClient::posY,
            ByteBufCodecs.FLOAT,
            QuenchMessageClient::posZ,
            ByteBufCodecs.BYTE,
            QuenchMessageClient::effect,
            QuenchMessageClient::new
        );
        
        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
}

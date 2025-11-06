package magma_monsters.network;

import magma_monsters.Reference;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class MagmaMonstersNetwork {

	public static void register(final RegisterPayloadHandlersEvent event) {
		PayloadRegistrar reg = event.registrar(Reference.MOD_ID);
		reg.playToClient(
				QuenchMessageClient.TYPE,
				QuenchMessageClient.STREAM_CODEC,
				QuenchMessage::handle
		    );
	}
}

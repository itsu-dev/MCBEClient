package jp.dip.hmy2001.mcbeClient.client;

import com.google.gson.Gson;
import com.whirvis.jraknet.session.RakNetServerSession;
import jp.dip.hmy2001.mcbeClient.network.mcbe.json.JwtHeader;
import jp.dip.hmy2001.mcbeClient.network.mcbe.json.SaltData;
import jp.dip.hmy2001.mcbeClient.network.mcbe.protocol.*;

import java.util.Base64;
import java.util.regex.Pattern;

public class PacketProcessor {

    private ClientListener clientListener;

    public PacketProcessor(ClientListener clientListener) {
        this.clientListener = clientListener;
    }

    public void process(RakNetServerSession session, byte[] pk) {
        byte packetId = pk[0];

        switch (packetId) {
            case ProtocolInfo.PLAY_STATUS_PACKET: {
                PlayStatusPacket receivePk = new PlayStatusPacket();
                receivePk.setBuffer(pk);
                receivePk.decode();

                if (receivePk.status == 3) {//Player Spawn
                    SetLocalPlayerAsInitializedPacket sendPk = new SetLocalPlayerAsInitializedPacket();
                    sendPk.entityRuntimeId = clientListener.getEntityRuntimeId();
                    clientListener.sendBatchPacket(session, sendPk);
                }
                break;
            }

            case ProtocolInfo.SERVER_TO_CLIENT_HANDSHAKE_PACKET: {
                ServerToClientHandshakePacket receivePk = new ServerToClientHandshakePacket();
                receivePk.setBuffer(pk);
                receivePk.decode();

                String[] jwtData = receivePk.jwt.split(Pattern.quote("."));

                Gson gson = new Gson();
                JwtHeader jwtHeader = gson.fromJson(new String(Base64.getUrlDecoder().decode(jwtData[0])), JwtHeader.class);
                SaltData saltData = gson.fromJson(new String(Base64.getUrlDecoder().decode(jwtData[1])), SaltData.class);

                clientListener.getClient().generateAESKey(jwtHeader.x5u, saltData.salt);
                clientListener.setEncryption(true);

                System.out.println("Enable Encryption!!!");

                ClientToServerHandshakePacket sendPk = new ClientToServerHandshakePacket();
                clientListener.sendBatchPacket(session, sendPk);
                break;
            }

            case ProtocolInfo.DISCONNECT_PACKET: {
                DisconnectPacket receivePk = new DisconnectPacket();
                receivePk.setBuffer(pk);
                receivePk.decode();

                if (!receivePk.hideDisconnectionScreen) {
                    System.out.println("DisconnectMessage: " + receivePk.message);
                }
                break;
            }

            case ProtocolInfo.RESOURCE_PACKS_INFO_PACKET: {
                ResourcePackClientResponsePacket sendPk = new ResourcePackClientResponsePacket();
                sendPk.status = 3;

                clientListener.sendBatchPacket(session, sendPk);
                break;
            }

            case ProtocolInfo.RESOURCE_PACK_STACK_PACKET: {
                ResourcePackClientResponsePacket sendPk = new ResourcePackClientResponsePacket();
                sendPk.status = 4;

                clientListener.sendBatchPacket(session, sendPk);
                break;
            }

            case ProtocolInfo.MOB_EQUIPMENT_PACKET: {
                MobEquipmentPacket receivePk = new MobEquipmentPacket();
                receivePk.setBuffer(pk);
                receivePk.decode();
                break;
            }

            case ProtocolInfo.START_GAME_PACKET: {
                StartGamePacket receivePk = new StartGamePacket();
                receivePk.setBuffer(pk);
                receivePk.decode();

                clientListener.setEntityUniqueId(receivePk.entityUniqueId);
                clientListener.setEntityRuntimeId(receivePk.entityRuntimeId);

                System.out.println("clientUniqueId: " + clientListener.getEntityUniqueId());
                System.out.println("clientRuntimeId: " + clientListener.getEntityRuntimeId());

                RequestChunkRadiusPacket sendPk = new RequestChunkRadiusPacket();
                sendPk.radius = 8;
                clientListener.sendBatchPacket(session, sendPk);
                break;
            }

            case ProtocolInfo.INVENTORY_CONTENT_PACKET: {
                InventoryContentPacket receivePk = new InventoryContentPacket();
                receivePk.setBuffer(pk);
                receivePk.decode();
                break;
            }

            case ProtocolInfo.CLIENTBOUND_MAP_ITEM_DATA_PACKET: {
                ClientboundMapItemDataPacket receivePk = new ClientboundMapItemDataPacket();
                receivePk.setBuffer(pk);
                receivePk.decode();
                break;
            }

            case ProtocolInfo.FULL_CHUNK_DATA_PACKET: {
                FullChunkDataPacket receivePk = new FullChunkDataPacket();
                receivePk.setBuffer(pk);
                receivePk.decode();
                break;
            }
            case ProtocolInfo.BOSS_EVENT_PACKET: {
                BossEventPacket receivePk = new BossEventPacket();
                receivePk.setBuffer(pk);
                receivePk.decode();
                break;
            }

            case ProtocolInfo.TEXT_PACKET: {
                TextPacket receivedPk = new TextPacket();
                receivedPk.setBuffer(pk);
                receivedPk.decode();
                clientListener.getClient().callPacketEventListener(receivedPk);
                break;
            }

            case ProtocolInfo.SET_TIME_PACKET: {
                SetTimePacket receivedPk = new SetTimePacket();
                receivedPk.setBuffer(pk);
                receivedPk.decode();
                clientListener.getClient().callPacketEventListener(receivedPk);
                break;
            }
        }
    }
}

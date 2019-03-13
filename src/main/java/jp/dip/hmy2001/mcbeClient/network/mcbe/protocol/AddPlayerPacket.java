package jp.dip.hmy2001.mcbeClient.network.mcbe.protocol;

import jp.dip.hmy2001.mcbeClient.network.mcbe.GamePacket;

import java.util.UUID;

public class AddPlayerPacket extends GamePacket {
    final byte NETWORK_ID = ProtocolInfo.ADD_PLAYER_PACKET;

    public UUID uuid;
    public String username;
    public long entityUniqueId;
    public long entityRuntimeId;
    public String platformChatId = "";
    public float x;
    public float y;
    public float z;
    public float speedX;
    public float speedY;
    public float speedZ;
    public float pitch;
    public float yaw;
    //public Item item;
    //public EntityMetadata metadata = new EntityMetadata();
    //public EntityLink links = new EntityLink[0];
    public String deviceId = "";

    public byte getPacketId() {
        return NETWORK_ID;
    }

    public void decodeBody() {
        this.uuid = readUUID();
        this.username = readString();
        this.entityUniqueId = readVarLong();
        this.entityRuntimeId = readVarLong();
        this.platformChatId = readString();
        this.x = readFloat();
        this.y = readFloat();
        this.z = readFloat();
        this.speedX = readFloat();
        this.speedY = readFloat();
        this.speedZ= readFloat();
        this.pitch = readFloatLE();
        this.yaw = readFloatLE();
        this.deviceId = readString();
    }
}
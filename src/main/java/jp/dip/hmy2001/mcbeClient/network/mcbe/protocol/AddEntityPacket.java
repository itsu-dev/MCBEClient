package jp.dip.hmy2001.mcbeClient.network.mcbe.protocol;

import jp.dip.hmy2001.mcbeClient.network.mcbe.GamePacket;

public class AddEntityPacket extends GamePacket {
    final byte NETWORK_ID = ProtocolInfo.ADD_ENTITY_PACKET;

    public long entityUniqueId;
    public long entityRuntimeId;
    public int type;
    public String id;
    public float x;
    public float y;
    public float z;
    public float speedX = 0f;
    public float speedY = 0f;
    public float speedZ = 0f;
    public float yaw;
    public float pitch;
    public float headYaw;
    //public EntityMetadata metadata = new EntityMetadata();
    //public Attribute[] attributes = new Attribute[0];
    public final Object[][] links = new Object[0][3];
    public int linksLength;

    public byte getPacketId() {
        return NETWORK_ID;
    }

    public void decodeBody() {
        this.entityUniqueId = readVarLong();
        this.entityRuntimeId = readVarLong();
        if (id == null) {

        }
        this.id = readString();
        this.x = readFloat();
        this.y = readFloat();
        this.z = readFloat();
        this.pitch = readFloatLE();
        this.yaw = readFloatLE();
        this.headYaw = readFloatLE();
        this.linksLength = readUnsignedVarInt();
    }
}
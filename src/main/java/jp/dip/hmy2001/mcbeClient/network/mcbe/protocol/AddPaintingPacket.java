package jp.dip.hmy2001.mcbeClient.network.mcbe.protocol;

import jp.dip.hmy2001.mcbeClient.network.mcbe.GamePacket;

public class AddPaintingPacket extends GamePacket {
    final byte NETWORK_ID = ProtocolInfo.ADD_PAINTING_PACKET;

    public long entityUniqueId;
    public long entityRuntimeId;
    public int x;
    public int y;
    public int z;
    public int direction;
    public String title;

    public byte getPacketId() {
        return NETWORK_ID;
    }

    public void decodeBody() {
        this.entityUniqueId = readVarLong();
        this.entityRuntimeId = readVarLong();
        this.x = readInt();
        this.y = readInt();
        this.z = readInt();
        this.direction = readVarInt();
        this.title = readString();
    }
}
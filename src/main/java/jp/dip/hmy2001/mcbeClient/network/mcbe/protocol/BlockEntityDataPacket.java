package jp.dip.hmy2001.mcbeClient.network.mcbe.protocol;

import jp.dip.hmy2001.mcbeClient.network.mcbe.GamePacket;

public class BlockEntityDataPacket extends GamePacket {
    final byte NETWORK_ID = ProtocolInfo.BLOCK_ENTITY_DATA_PACKET;

    public int x;
    public int y;
    public int z;
    public byte[] namedTag;

    public byte getPacketId() {
        return NETWORK_ID;
    }

    public void decodeBody() {
        this.x = readVarInt();
        this.y = readVarInt();
        this.z = readVarInt();
        //this.namedTag = read(namedTag.length);
    }

    public void encodeBody() {
        writeVarInt(this.x);
        writeVarInt(this.y);
        writeVarInt(this.z);
        write(this.namedTag);
    }
}
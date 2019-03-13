package jp.dip.hmy2001.mcbeClient.network.mcbe.protocol;

import jp.dip.hmy2001.mcbeClient.network.mcbe.GamePacket;

public class BlockEventPacket extends GamePacket {
    final byte NETWORK_ID = ProtocolInfo.BLOCK_EVENT_PACKET;

    public int x;
    public int y;
    public int z;
    public int case1;
    public int case2;

    public byte getPacketId() {
        return NETWORK_ID;
    }

    public void decodeBody() {
        this.x = readVarInt();
        this.y = readVarInt();
        this.z = readVarInt();
        this.case1 = readVarInt();
        this.case2 = readVarInt();
    }
}

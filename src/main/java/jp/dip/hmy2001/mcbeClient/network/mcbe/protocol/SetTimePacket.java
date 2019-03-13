package jp.dip.hmy2001.mcbeClient.network.mcbe.protocol;

import jp.dip.hmy2001.mcbeClient.network.mcbe.GamePacket;

public class SetTimePacket extends GamePacket {
    final byte NETWORK_ID = ProtocolInfo.SET_TIME_PACKET;

    public int time;

    public byte getPacketId() {
        return NETWORK_ID;
    }

    public void decodeBody() {
        this.time = readVarInt();
    }
}

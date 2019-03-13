package jp.dip.hmy2001.mcbeClient.network.mcbe.protocol;

import jp.dip.hmy2001.mcbeClient.network.mcbe.GamePacket;

public class AnimatePacket extends GamePacket {
    final byte NETWORK_ID = ProtocolInfo.ANIMATE_PACKET;

    public long eid;
    public int action;
    public float unknown;

    public byte getPacketId() {
        return NETWORK_ID;
    }

    public void decodeBody() {
        this.action = readVarInt();
        this.eid = readVarLong();
        if ((this.action & 0x80) != 0) {
            this.unknown = readFloatLE();
        }
    }

    public void encodeBody() {
        this.writeVarInt(this.action);
        this.writeVarLong((int) eid);
        if ((this.action & 0x80) != 0) {
            this.writeFloatLE(this.unknown);
        }
    }
}
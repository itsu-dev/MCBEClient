package jp.dip.hmy2001.mcbeClient.network.mcbe.protocol;

import jp.dip.hmy2001.mcbeClient.network.mcbe.GamePacket;

public class AddBehaviorTreePacket extends GamePacket {

    final byte NETWORK_ID = ProtocolInfo.ADD_BEHAVIOR_TREE_PACKET;

    public String unknown;

    public byte getPacketId() {
        return NETWORK_ID;
    }

    public void decodeBody() {
        this.unknown = readString();
    }
}

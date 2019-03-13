package jp.dip.hmy2001.mcbeClient.listener;

import jp.dip.hmy2001.mcbeClient.network.mcbe.protocol.SetTimePacket;
import jp.dip.hmy2001.mcbeClient.network.mcbe.protocol.TextPacket;

public interface PacketEventListener {

    void onTextPacket(TextPacket packet);
    void onSetTimePacket(SetTimePacket packet);

}

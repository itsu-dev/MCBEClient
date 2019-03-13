package jp.dip.hmy2001.mcbeClient.listener;

import jp.dip.hmy2001.mcbeClient.network.mcbe.protocol.SetTimePacket;
import jp.dip.hmy2001.mcbeClient.network.mcbe.protocol.TextPacket;

public class SystemEventListener implements PacketEventListener {

    @Override
    public void onTextPacket(TextPacket packet) {
        switch (packet.type) {
            case TextPacket.TYPE_TRANSLATION:
            case TextPacket.TYPE_ANNOUNCEMENT: {
                if (packet.parameters.length >= 2) {
                    System.out.println(escapeText("[" + packet.parameters[0] + "] " + packet.parameters[1]));
                }
                break;
            }

            case TextPacket.TYPE_RAW: {
                System.out.println(escapeText(packet.message));
                break;
            }
        }
    }

    @Override
    public void onSetTimePacket(SetTimePacket packet) {
        System.out.println("Set time: " + packet.time);
    }

    private String escapeText(String text) {
        text = text.replaceAll("§1", "");
        text = text.replaceAll("§2", "");
        text = text.replaceAll("§3", "");
        text = text.replaceAll("§4", "");
        text = text.replaceAll("§5", "");
        text = text.replaceAll("§6", "");
        text = text.replaceAll("§7", "");
        text = text.replaceAll("§8", "");
        text = text.replaceAll("§9", "");
        text = text.replaceAll("§0", "");
        text = text.replaceAll("§a", "");
        text = text.replaceAll("§b", "");
        text = text.replaceAll("§c", "");
        text = text.replaceAll("§d", "");
        text = text.replaceAll("§e", "");
        text = text.replaceAll("§f", "");
        text = text.replaceAll("§r", "");
        text = text.replaceAll("§o", "");
        return text;
    }
}

package jp.dip.hmy2001.mcbeClient.network.mcbe.protocol;

import jp.dip.hmy2001.mcbeClient.network.mcbe.GamePacket;

public class TextPacket extends GamePacket {
    final byte NETWORK_ID = ProtocolInfo.TEXT_PACKET;

    public static final byte TYPE_RAW = 0;
    public static final byte TYPE_CHAT = 1;
    public static final byte TYPE_TRANSLATION = 2;
    public static final byte TYPE_POPUP = 3;
    public static final byte TYPE_JUKEBOX_POPUP = 4;
    public static final byte TYPE_TIP = 5;
    public static final byte TYPE_SYSTEM = 6;
    public static final byte TYPE_WHISPER = 7;
    public static final byte TYPE_ANNOUNCEMENT = 8;
    public static final byte TYPE_JSON = 9;

    public byte type;
    public String source = "";
    public String message = "";
    public String[] parameters = new String[0];
    public boolean isLocalized = false;
    public String platformChatId = "";

    public byte getPacketId() {
        return NETWORK_ID;
    }

    public void decodeBody(){
        this.type = this.readByte();
        this.isLocalized = this.readBoolean() || type == TYPE_TRANSLATION;

        switch (type) {
            case TYPE_POPUP:
            case TYPE_CHAT:
            case TYPE_WHISPER:
            case TYPE_ANNOUNCEMENT:
                this.source = this.readString();
            case TYPE_RAW:
            case TYPE_TIP:
            case TYPE_SYSTEM:
            case TYPE_JSON:
                this.message = this.readString();
                break;

            case TYPE_TRANSLATION:
            case TYPE_JUKEBOX_POPUP:
                this.message = this.readString();
                int count = this.readUnsignedVarInt();
                this.parameters = new String[count];
                for (int i = 0; i < count; i++) {
                    this.parameters[i] = this.readString();
                }
        }

        this.platformChatId = this.readString();
    }

}
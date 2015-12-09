package com.gofriend.vienngan.vngofriend;

/**
 * Created by tmvien on 12/9/15.
 */
public enum Skin {

    STANDARD(25933399), BALLOON(1816259760);

    private static Skin sCurrentSkin;

    private int mId;


    public static void setCurrentSkin(Skin skin) {
        sCurrentSkin = skin;
    }

    public static void setCurrentSkin(int skinId) {
        for (Skin skin : Skin.values()) {
            if (skin.mId == skinId) {
                setCurrentSkin(skin);
                return;
            }
        }
        throw new AssertionError();
    }

    public static Skin getCurrentSkin() {
        return sCurrentSkin;
    }

    private Skin(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

}

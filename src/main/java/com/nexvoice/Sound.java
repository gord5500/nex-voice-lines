package com.nexvoice;


public enum Sound {
    SIPHON("a siphon.wav"),
    AT_LAST("at last.wav"),
    CONTAIN_THIS("contain this.wav"),
    CRUOR("cruor.wav"),
    CRUOR_DONT_FAIL_ME("cruor dont fail me.wav"),
    DARKEN_MY_SHADOW("darken my shadow.wav"),
    DIE_NOW("die now.wav"),
    EMBRACE_DARKNESS("embrace darkness.wav"),
    FEAR_THE_SHADOW("fear the shadow.wav"),
    FILL_MY_SOUL("fill my soul with smoke.wav"),
    FLOOD_MY_LUNGS("flood my lungs.wav"),
    FUMUS("fumus.wav"),
    FUMUS_DONT_FAIL_ME("fumus dont fail me.wav"),
    GLACIES("glacies.wav"),
    GLACIES_DONT_FAIL_ME("glacies dont fail me.wav"),
    I_DEMAND_A_BLOOD("i demand a blood sacrifice.wav"),
    POWER_OF_ICE("infuse me with the power of ice.wav"),
    LET_THE_VIRUS("let the virus.wav"),
    NO_ESCAPE("no escape.wav"),
    POWER_OF_ZAROS("now the power of zaros.wav"),
    WRATH("taste my wrath.wav"),
    THERE_IS("there is.wav"),
    UMBRA("umbra.wav"),
    UMBRA_DONT_FAIL_ME("umbra dont fail me.wav");

    private final String resourceName;

    Sound(String resNam) {
        resourceName = resNam;
    }

    String getResourceName() {
        return resourceName;
    }
}
package lol;

import lol.content.*;

import arc.*;
import arc.util.*;
import mindustry.game.EventType;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class LolMod {
    public lol2() {
    }

    public static String ModName = "lol2";

    public static String name(String add) {
        return ModName + "-" + add;
    }

    @Override
    public void loadContent() {
        Items.load();
    }
}

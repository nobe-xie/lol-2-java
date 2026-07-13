package lol.content;

public class LolItems {
    public static Item 
    SuperconductingMaterials, SiliconNitride, TitaniumSteel
	;
    public static void load(){
        a = new Items("Superconducting-materials". Color.valueOf("DEDEDEFF")){{
            flammability= 0f;
	        explosiveness= 0f;
	        radioactivity= 0f;
	        charge= 1.5f;
	        cost= 1.6f;
        }};

        SiliconNitride = new Item("Silicon-nitride", Color.valueOf("D5E3E2FF")){{
            hardness = 2;
	        cost = 1;
	        healthScaling = 0.6;
	        alwaysUnlocked = false;
	        radioactivity = 0;
	        explosiveness = 0;
	        flammability = 0;
	        charge = 0;
        }};
    }
}

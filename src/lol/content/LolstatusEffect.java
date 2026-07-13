package lol.content;

import arc.graphics.*;
import arc.math.Mathf;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.entities.effect.WrapEffect;
import mindustry.entities.units.StatusEntry;
import mindustry.gen.Unit;
import mindustry.graphics.*;
import arc.math.Interp;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.type.StatusEffect;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;
import static mindustry.content.StatusEffects.*;

public class LolstatusEffect {
    public static StatusEffect
    Radiation;

    Radiation = new StatusEffect("radiation") {{
        color = Pal.heal;
        damage = 1f;
        reloadMultiplier = 0.75f;
        speedMultiplier = 0.8f;
        effect  = new ParticleEffect() {{
            particles = 3;
            baseLength = 0;
            length = 4f;
            lifetime = 60f;
            spin = 3;
            region = "shell";
            sizeFrom = 15f;
            sizeTo = 0f;
            colorFrom = AFA1FFFF;
            colorTo = A1CEFFFF;
            cone = 360
        }};
    }};
}

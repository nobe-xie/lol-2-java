package lol.content;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.*;
import arc.math.geom.Vec2;
import arc.struct.Seq;
import arc.util.Tmp;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.entities.UnitSorts;
import mindustry.entities.abilities.MoveEffectAbility;
import mindustry.entities.abilities.StatusFieldAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.HaloPart;
import mindustry.entities.part.RegionPart;
import mindustry.entities.part.ShapePart;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.CacheLayer;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.type.unit.MissileUnitType;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.liquid.ArmoredConduit;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.blocks.liquid.LiquidBridge;
import mindustry.world.blocks.liquid.LiquidRouter;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static lol.LolMod.name;
import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.*;
import static arc.math.Mathf.*;
import static mindustry.type.ItemStack.*;

public class Lolblocks {
    public static Block
    //power
    FusionReactor,

    //turrets
    RailGun;

    public static void load() {
        RailGun = new ItemTurret("Calamity") {{
            size = 4;
            range = 800;
            health = 1000;
            shake = 5;
            requirements(Category.turret, with(Items.plastanium, 700, Items.titanium, 750, Items.surgeAlloy, 250, Items.silicon, 500, LolItems.SiliconNitride ,350 ));
            recoil = 8;
            rotateSpeed = 1.5f;
            shootCone = 3;
            reload = 240;
            cooldownTime = 200f;
            
            ammoUseEffect = casing3Double;
            ammoPerShot = 4;
            maxAmmo = 48;
            consumePower(5f);
            ammo(
                    Items.surgeAlloy, new RailBulletType(){{
                        damage = 1580;
                        buildingDamageMultiplier = 0.3f;
                        pierceDamageFactor = 0.8;
                        length = 800f;
                        hitShake = 8;
                        shootEffect = instShoot;
                        hitEffect = instHit;
                        pierceEffect = railHit;
                        smokeEffect = smokeCloud;
                        pointEffect:instTrail;
                        despawnEffect = instBomb;
                }}
            )
        }};

        FusionReactor = new ImpactReactor("FusionReactor") {{
            size = 8;
            health = 1500;
            hasItems = hasLiquids = haspower = outputsPower = true;
            requirements(Category.power, with(Items.metaglass,750, Items.plastanium,300, Items.silicon,700, Items.surgeAlloy,200, LolItems.titaniumSteel,850));
            rebuildable = false;
            itemCapacity = 25;
            liquidCapacity = 450;
            warmupSpeed = 0.001f;

            powerProduction = 200;
            consumePower(25f);
            consumeLiquid(Liquids.cryofluid, 2.4f, Liquids.hydrogen, 3f);
            consumeItems(with(Items.thorium, 5));
            itemDuration = 60f;

            explosionShakeDuration = 480;
            explosionDamage = 10000;
            explosionRadius = 640;
            explosionMinWarmup = 0.8f;
            explodeSound = SFSounds.hugeExplosion;
            explodeEffect = new MultiEffect(
                Fx.impactReactorExplosion,
                new WaveEffect() {{
                    lifetime = 39f;
                    sizeFrom = 0;
                    sizeTo = 640;
                    strokeTo = 0;
                    colorFrom = EEC591;
                    colorTo = FFFFFF;
                }};
                new ParticleEffect() {{
                    particles = 24;
                    interp = pow10Out;
                    sizeInterp = pow5In;
                    sizeFrom = 35;
                    sizeTo = 280;
                    baseLength = 280
                    lifetime = 120f;
                    colorFrom = colorTo = DEDEDE70;
                }};
            )
            
            destroyBullet = new ExplosionBulletType(3000, 120) {{
                makeFire = true;
                hitSound = SFSounds.hugeExplosion;
                hitSoundVolume = 3;
                hitShake = 50f;
                hitEffect = new WaveEffect() {{
                    lifetime = 60f;
                    sizeFrom = 20f;
                    sizeTo = 530f;
                    strokeFrom = 30;
                    interp = Interp.circleOut;
                    colorTo = EEC591;
                    colorFrom = FFFFFF;
                }};
                status = LolStatusEffect.Radiation;
                statusDuration = 1200;
                despawnEffect = new MultiEffect(
                        Fx.impactReactorExplosion,
                        new ParticleEffect() {{
                            particles = 1;
                            sizeFrom = 10;
                            sizeTo = 480f;
                            length = 0;
                            lifetime = 22f;
                            sizeInterp = Interp.pow10Out;
                            colorTo = SFColor.discLight;
                        }},
                        new ParticleEffect() {{
                            particles = 1;
                            sizeFrom = 480;
                            startDelay = 19f;
                            length = 0;
                            lifetime = 60;
                            colorFrom = colorTo = SFColor.discLight;
                        }}
                );
                fragBullets = 60;
                fragLifeMax = 1.1f;
                fragLifeMin = 0.12;
                fragBullet = new BasicBulletType(50, 120, "circle-bullet") {{
                    hitable = false;
                    absorbable = true;
                    splashDamage = 800;
                    splashDamageRadius = 160;
                    status = LolStatusEffect.Radiation;
                    statusDuration = 120;
                    speed = 30f;
                    collidesTeam = collidesAir = collidesGround = collidesTiles = true;
                    drag = 0.05f;
                    hitShake = 25f;
                    lifetime = 600f;
                    hitSound = explosionbig;
                    hitSoundVolume = 7f;
                    width = height = 6;
                    shrinkX = 0f;
                    trailLength = 18f;
                    trailWidth = 2.25;
                    trailColor = EEC59190;
                }};
            }};
        }};
    }
}

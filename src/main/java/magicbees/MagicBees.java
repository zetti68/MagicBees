package magicbees;

import elec332.core.compat.forestry.bee.BlockHive;
import elec332.core.item.ItemEnumBased;
import elec332.core.util.LoadTimer;
import magicbees.bees.AlleleInit;
import magicbees.bees.EnumBeeModifiers;
import magicbees.bees.EnumBeeHives;
import magicbees.item.ItemBeeFrames;
import magicbees.item.types.EnumCombType;
import magicbees.util.MoreBeesResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

/**
 * Created by Elec332 on 16-8-2016.
 */
@Mod(modid = MagicBees.modid, name = MagicBees.modName, dependencies = "required-after:ElecCore")
public class MagicBees {

    public static final String modid = "magicbees";
    public static final String modName = "MagicBees";

    @Mod.Instance(modid)
    public static MagicBees instance;
    public static Logger logger;
    private static LoadTimer loadTimer;

    public static BlockHive<EnumBeeHives> hiveBlock;
    public static ItemEnumBased<EnumCombType> combItem;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = LogManager.getLogger(modName);
        loadTimer = new LoadTimer(logger, modName);
        loadTimer.startPhase(event);

        loadTimer.endPhase(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        loadTimer.startPhase(event);
        AlleleInit.init();
        hiveBlock = new BlockHive<EnumBeeHives>() {

            @Nonnull
            @Override
            public Class<EnumBeeHives> getHiveTypes() {
                return EnumBeeHives.class;
            }

        }.register(new ResourceLocation(modid, "hiveBlock"));
        GameRegistry.register(new ItemBeeFrames(EnumBeeModifiers.CREATIVE));
        combItem = GameRegistry.register(new ItemEnumBased<>(new MoreBeesResourceLocation("beeComb"), EnumCombType.class));

        loadTimer.endPhase(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        loadTimer.startPhase(event);

        loadTimer.endPhase(event);
    }

}
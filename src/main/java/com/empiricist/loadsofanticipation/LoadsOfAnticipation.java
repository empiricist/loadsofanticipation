package com.empiricist.loadsofanticipation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = LoadsOfAnticipation.MODID, version = LoadsOfAnticipation.VERSION)
public class LoadsOfAnticipation
{
    public static final String MODID = "loadsofanticipation";
    public static final String VERSION = "1.0";
    
    @EventHandler
    @SuppressWarnings("unused")
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);//so the event functions actually get called
    }


    @SubscribeEvent
    @SuppressWarnings("unused")
    public void screenOpens( GuiOpenEvent event ){
//        String guiName;
//        if(event.getGui() == null){
//            guiName = "null";
//        }else if(event.getGui().getClass() == null){
//            guiName = "classless";
//        }else if(event.getGui().getClass().getName() == null){
//            guiName = "nameless";
//        }else{
//            guiName = event.getGui().getClass().getName();
//        }
//
//        System.out.println(">>>>        GUI opened!  It is " + guiName );

        if( event.getGui() instanceof GuiDownloadTerrain ){
            event.setGui( new GuiDownloadTerrainPlus(Minecraft.getMinecraft().getConnection(), Minecraft.getMinecraft().theWorld.provider.getDimension()) );
        }
    }
}

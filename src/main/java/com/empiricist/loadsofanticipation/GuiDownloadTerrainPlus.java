package com.empiricist.loadsofanticipation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.DimensionManager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GuiDownloadTerrainPlus extends GuiDownloadTerrain {

    private ResourceLocation dimImg;
    private int dimension;

    public GuiDownloadTerrainPlus(NetHandlerPlayClient netHandler, int dimID) {
        super(netHandler);
        dimension = dimID;
        try{
            dimImg = new ResourceLocation( LoadsOfAnticipation.MODID + ":" + "textures/guis/" + dimID + ".png");//we'll load these as needed I guess?
            new SimpleTexture(dimImg).loadTexture( Minecraft.getMinecraft().getResourceManager() );//check if the texture really exists
        }catch(IOException e){
            System.out.println("Problem loading dimension image for dimension " + dimID  );
            dimImg = new ResourceLocation( LoadsOfAnticipation.MODID + ":textures/guis/err.png");//if no texture is found for this dimension, use the default error one
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        int bezel = height/30;
        this.drawCenteredString(this.fontRendererObj, I18n.format("multiplayer.downloadingTerrain", new Object[0]), this.width/2, this.height/4 - bezel - fontRendererObj.FONT_HEIGHT-2, 0xFFFFFF);//Loading terrain
        this.drawCenteredString(this.fontRendererObj, I18n.format(LoadsOfAnticipation.MODID + ":announcedim", new Object[0]), this.width/2, this.height/2 + height/4 + bezel + 2, 0xFFFFFF);//Now entering...

        String dimName = I18n.format(LoadsOfAnticipation.MODID + ":dim." + dimension + ".name", new Object[0]);//check for name in lang file
        if( dimName.equals(LoadsOfAnticipation.MODID + ":dim." + dimension + ".name") && DimensionManager.isDimensionRegistered(dimension)){//if it didn't find one, try the in-game name
            dimName = DimensionManager.getProvider(dimension).getDimensionType().getName();
        }

        GlStateManager.scale(1.5, 1.5, 1.5);//make name bigger
        this.drawCenteredString(this.fontRendererObj, dimName, this.width/3, (this.height/2 + height/4 + bezel + 2 + fontRendererObj.FONT_HEIGHT + height*185/9990)*2/3, 0xFFFFFF);
        GlStateManager.scale(0.6667, 0.6667, 0.6667);//back to normal size

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(dimImg);
        GlStateManager.enableBlend();
        drawRect( width/4-bezel, height/4-bezel, width/2+width/4+bezel, height/2+height/4+bezel, 0x59FFFFFF );//white and ~35% opaque
        Gui.drawModalRectWithCustomSizedTexture( width/4, height/4, 0.0F, 0.0F, width/2, height/2, width/2f, height/2f );//screenshot of dimension or something
        GlStateManager.disableBlend();
    }
}

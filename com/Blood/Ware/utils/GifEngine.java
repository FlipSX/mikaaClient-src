package com.Blood.Ware.utils;

import com.Blood.Ware.BloodWare;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

public class GifEngine {
   private int current = 0;
   private Counter counter = new Counter();
   private DynamicTexture texture;
   private List<DynamicTexture> frames;
   private int x;
   private int y;
   private int width;
   private int height;
   private int tiktok;
   private int ticks;

   public GifEngine(ResourceLocation rs, int width, int height, int tt) {
      this.texture = new DynamicTexture(width, height);
      this.width = width;
      this.height = height;
      this.frames = this.images(rs);
      this.ticks = tt;
   }

   public void update() {
      if (this.tiktok > this.ticks) {
         if (this.counter.hasReached(Minecraft.getMinecraft().getTickLength()) && this.frames.size() > 0) {
            if (this.current > this.frames.size() - 1) {
               this.current = 0;
            }

            this.texture = (DynamicTexture)this.frames.get(this.current);
            ++this.current;
            this.counter.reset();
         }

         this.tiktok = 0;
      }

      ++this.tiktok;
   }

   public void bind(int x, int y) {
      this.x = x;
      this.y = y;
      UIRender.bind((float)this.x + 5.0F, (float)this.y, (float)(this.width / BloodWare.scale.getScale()), (float)(this.height / BloodWare.scale.getScale()), 1.0F, this.getTexture().func_110552_b());
   }

   public List<DynamicTexture> images(ResourceLocation rs) {
      ArrayList images = new ArrayList();

      try {
         ImageReader reader = (ImageReader)ImageIO.getImageReadersByFormatName("gif").next();
         InputStream xui = Minecraft.getMinecraft().getResourceManager().getResource(rs).getInputStream();
         ImageInputStream stream = ImageIO.createImageInputStream(xui);
         reader.setInput(stream);
         int count = reader.getNumImages(true);

         for(int index = 0; index < count; ++index) {
            BufferedImage frame = reader.read(index);
            images.add(new DynamicTexture(frame));
         }
      } catch (IOException var9) {
      }

      return images;
   }

   public DynamicTexture getTexture() {
      return this.texture;
   }

   public void setTexture(DynamicTexture texture) {
      this.texture = texture;
   }

   public List<DynamicTexture> getFrames() {
      return this.frames;
   }

   public void setFrames(List<DynamicTexture> frames) {
      this.frames = frames;
   }
}

package com.stockcontroll.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService{
    @Override
    public byte[] getImage() {
        try {
            BufferedImage bufferimage = ImageIO.read( new ClassPathResource("static/almacen.jpeg").getFile() );
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageIO.write(bufferimage, "jpg", output );
            return output.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }

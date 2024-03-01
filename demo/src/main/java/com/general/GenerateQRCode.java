package com.general;

import com.client.Ticket;
import com.spire.barcode.BarCodeGenerator;
import com.spire.barcode.BarCodeType;
import com.spire.barcode.BarcodeSettings;
import com.spire.barcode.QRCodeECL;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Image;


public class GenerateQRCode {

    public void generateQrCode(String data , String ticketID) throws IOException {
           //Instantiate a BarcodeSettings object

           String qrPath = "ticket_"+ticketID+".png";

           BarcodeSettings settings = new BarcodeSettings();
           //Set barcode type
           settings.setType(BarCodeType.QR_Code);
           //Set barcode data
           settings.setData(data);
           //Set barcode module width
           settings.setX(2);
           //Set error correction level
           settings.setQRCodeECL(QRCodeECL.M);
   
           //Set top text
           settings.setTopText("CinemaX");
           //Set bottom text
           settings.setBottomText("Seat No. "+ ticketID);
   
           //Set text visibility
           settings.setShowText(false);
           settings.setShowTopText(true);
           settings.setShowBottomText(true);
   
           //Set border visibility
           settings.hasBorder(false);
   
           //Instantiate a BarCodeGenerator object based on the specific settings
           BarCodeGenerator barCodeGenerator = new BarCodeGenerator(settings);
           //Generate QR code image
           BufferedImage bufferedImage = barCodeGenerator.generateImage();
           //save the image to a .png file
           ImageIO.write(bufferedImage,"png",new File(qrPath));

         /*   this.openQrCode(qrPath); */
         String imagePath = qrPath;

         // Create a new JFrame
         JFrame frame = new JFrame("Display Image");
 
         // Create a new JPanel
         JPanel panel = new JPanel();
 
         // Load the image using ImageIcon
         ImageIcon icon = new ImageIcon(imagePath);
         Image image = icon.getImage();
 
         // Create a JLabel to hold the image
         JLabel label = new JLabel();
         label.setIcon(icon);
 
         // Add the JLabel to the JPanel
         panel.add(label);
 
         // Add the JPanel to the JFrame
         frame.add(panel);
 
         // Set the size of the JFrame
         frame.setSize(new Dimension(image.getWidth(null), image.getHeight(null)));
 
         // Set the default close operation of the JFrame
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
         // Make the JFrame visible
         frame.setVisible(true);
    }

    public void openQrCode(String filePathQr){

        String filePath = filePathQr; // Path to your PNG file

        try {
            // Read the PNG file into a BufferedImage
            BufferedImage image = ImageIO.read(new File(filePath));

            // Check if the image is successfully loaded
            if (image != null) {
                // Display some information about the image
                int width = image.getWidth();
                int height = image.getHeight();
                int type = image.getType();
                System.out.println("Image dimensions: " + width + "x" + height);
                System.out.println("Image type: " + type);
            } else {
                System.out.println("Failed to read the image.");
            }
        } catch (IOException e) {
            System.out.println("Error reading the image: " + e.getMessage());
        }

    }
}
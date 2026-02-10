package com.ui.javafx;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IconGenerator {
    // Generate simple square PNG placeholders with a letter label
    public static void ensureIconsExist() {
        String[] names = {"dashboard", "loan", "incident", "persons", "reports"};
        String projectRoot = System.getProperty("user.dir");
        File iconsDir = new File(projectRoot, "src/main/resources/icons");
        if (!iconsDir.exists()) {
            boolean created = iconsDir.mkdirs();
            if (!created) {
                System.err.println("No se pudo crear el directorio de iconos: " + iconsDir.getAbsolutePath());
                // still proceed: creation may fail if running in read-only environment
            }
        }

        for (String n : names) {
            File f = new File(iconsDir, n + ".png");
            if (!f.exists()) {
                try {
                    createPlaceholder(f, n.substring(0,1).toUpperCase());
                } catch (IOException ex) {
                    System.err.println("No se pudo crear icono: " + f.getAbsolutePath() + " -> " + ex.getMessage());
                }
            }
        }
    }

    private static void createPlaceholder(File f, String label) throws IOException {
        int size = 128;
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        try {
            // background
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(new Color(0x00A0FF));
            g.fillRoundRect(0,0,size,size,20,20);
            // letter
            g.setColor(Color.WHITE);
            Font font = new Font("SansSerif", Font.BOLD, 72);
            g.setFont(font);
            FontMetrics fm = g.getFontMetrics();
            int w = fm.stringWidth(label);
            int h = fm.getAscent();
            g.drawString(label, (size - w)/2, (size + h)/2 - 10);
        } finally {
            g.dispose();
        }
        ImageIO.write(img, "PNG", f);
    }

    // método main para ejecutar desde la línea de comandos y generar los iconos en el repo
    public static void main(String[] args) {
        ensureIconsExist();
        System.out.println("Iconos generados (si no existían) en src/main/resources/icons/");
    }
}

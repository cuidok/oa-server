package com.github.cuidok.oa.server.verification;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

@Service
public class LoginVerificationImageGenerator {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 30;
    private static final int FONT_SIZE = 20;
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color FONT_COLOR = Color.BLACK;
    private static final int LINE_COUNT = 3;
    private static final int NOISE_POINT_COUNT = 30;

    public byte[] generateImage(String verificationCode) {

        if (verificationCode == null || verificationCode.isEmpty()) {
            throw new IllegalArgumentException("Verification code cannot be null or empty");
        }

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        // Draw the verification container
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw the verification code
        g.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        g.setColor(FONT_COLOR);
        FontMetrics fontMetrics = g.getFontMetrics();
        int charWidth = fontMetrics.charWidth('A'); // Assuming all characters have the same width
        int totalWidth = charWidth * verificationCode.length();
        int x = (WIDTH - totalWidth) / 4;
        int y = ((HEIGHT - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();
        for (char c : verificationCode.toCharArray()) {
            g.drawString(String.valueOf(c), x, y);
            x += charWidth + 8;
        }

        // Draw some colorful lines
        Random random = new Random();
        for (int i = 0; i < LINE_COUNT; i++) {
            g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            int x2 = random.nextInt(WIDTH);
            int y2 = random.nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }

        // Draw some noise points
        for (int i = 0; i < NOISE_POINT_COUNT; i++) {
            g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            g.drawRect(x1, y1, 1, 1);
        }

        g.dispose();

        // Convert the image to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return baos.toByteArray();
    }
}

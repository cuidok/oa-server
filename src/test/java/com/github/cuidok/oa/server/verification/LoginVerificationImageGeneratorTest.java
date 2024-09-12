package com.github.cuidok.oa.server.verification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginVerificationImageGeneratorTest {

    @Autowired
    private LoginVerificationImageGenerator loginVerificationImageGenerator;

    @Test
    void generateImage() {
        try {
            byte[] imageBytes = loginVerificationImageGenerator.generateImage("abcd");

            // Convert byte array back to BufferedImage
            ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
            BufferedImage image = ImageIO.read(bais);

            // Write the image to a file
            File outputfile = new File("./target/generated-verification-code-image.png");
            ImageIO.write(image, "png", outputfile);

            System.out.println("Image saved as /target/generated-verification-code-image.png");
        } catch (Exception e) {
            fail(e);
        }
    }
}
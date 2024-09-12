package com.github.cuidok.oa.server.verification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class LoginVerificationCodeServerTest {

    @Autowired
    private LoginVerificationCodeService loginVerificationCodeService;

    @Autowired
    private LoginVerificationKeyService loginVerificationKeyService;

    @Test
    void generateCode() {

        String key = loginVerificationKeyService.generateKey();

        byte[] imageBytes = loginVerificationCodeService.generateCodeImage(key);

        try {
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
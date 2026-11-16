package vulnlab.ssrf.spring;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.springframework.web.bind.annotation.*;

/** ImageIO.read(File) lit un fichier : pas une requête HTTP (c'est la règle pathtraver qui s'en charge). */
@RestController
public class SsrfSpringImageIoFile {
    @GetMapping("/ssrf/imageiofile")
    public String handle(@RequestParam String name) throws Exception {
        BufferedImage image = ImageIO.read(new File("/var/app/images", name));
        return image == null ? "inconnu" : image.getWidth() + "x" + image.getHeight();
    }
}

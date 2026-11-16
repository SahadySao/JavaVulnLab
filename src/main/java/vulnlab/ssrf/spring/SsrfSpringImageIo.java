package vulnlab.ssrf.spring;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import org.springframework.web.bind.annotation.*;

/** ImageIO.read(URL) télécharge l'image : http://169.254.169.254/... passe. */
@RestController
public class SsrfSpringImageIo {
    @GetMapping("/ssrf/imageio")
    public String handle(@RequestParam String url) throws Exception {
        BufferedImage image = ImageIO.read(new URL(url));
        return image == null ? "inconnu" : image.getWidth() + "x" + image.getHeight();
    }
}

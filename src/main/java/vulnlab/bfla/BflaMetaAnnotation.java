package vulnlab.bfla;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/** Annotation maison @AdminOnly, elle-même annotée @PreAuthorize. */
@RestController
public class BflaMetaAnnotation {
    @AdminOnly
    @PostMapping("/bfla/management/settings/reset")
    public void resetSettings() {
    }
}

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('ADMIN')")
@interface AdminOnly {
}

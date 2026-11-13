package vulnlab.bfla;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/** Purge du cache : le contrôleur ne vérifie rien, le service exige ADMIN (@PreAuthorize sur la couche service). */
@RestController
public class BflaServiceLayerRole {
    private final CacheAdminService cacheAdminService;

    public BflaServiceLayerRole(CacheAdminService cacheAdminService) {
        this.cacheAdminService = cacheAdminService;
    }

    @PostMapping("/bfla/management/cache/purge")
    public void purge() {
        cacheAdminService.purgeAll();
    }
}

@Service
class CacheAdminService {
    @PreAuthorize("hasRole('ADMIN')")
    public void purgeAll() {
    }
}

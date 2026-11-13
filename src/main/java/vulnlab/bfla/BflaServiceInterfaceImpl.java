package vulnlab.bfla;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/** Le contrôleur appelle une interface ; l'implémentation porte @PreAuthorize. */
@RestController
public class BflaServiceInterfaceImpl {
    private final TenantAdminService tenantAdminService;

    public BflaServiceInterfaceImpl(TenantAdminService tenantAdminService) {
        this.tenantAdminService = tenantAdminService;
    }

    @DeleteMapping("/bfla/management/tenants/{id}")
    public void deleteTenant(@PathVariable long id) {
        tenantAdminService.delete(id);
    }
}

interface TenantAdminService {
    void delete(long id);
}

@Service
class TenantAdminServiceImpl implements TenantAdminService {
    @Override
    @PreAuthorize("hasAuthority('TENANT_DELETE')")
    public void delete(long id) {
    }
}

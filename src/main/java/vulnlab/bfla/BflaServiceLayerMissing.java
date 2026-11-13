package vulnlab.bfla;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/** Suspension de compte : ni le contrôleur ni le service ne vérifient le rôle. */
@RestController
public class BflaServiceLayerMissing {
    private final AccountAdminService accountAdminService;

    public BflaServiceLayerMissing(AccountAdminService accountAdminService) {
        this.accountAdminService = accountAdminService;
    }

    @PostMapping("/bfla/management/accounts/{id}/suspend")
    public void suspend(@PathVariable long id) {
        accountAdminService.suspend(id);
    }
}

@Service
class AccountAdminService {
    public void suspend(long id) {
    }
}

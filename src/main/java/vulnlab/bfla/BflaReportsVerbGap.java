package vulnlab.bfla;

import org.springframework.web.bind.annotation.*;

/** La règle MANAGER ne vise que GET /bfla/reports/** : en POST, l'archivage tombe sur anyRequest().authenticated(). */
@RestController
public class BflaReportsVerbGap {
    @PostMapping("/bfla/reports/{id}/archive")
    public void archive(@PathVariable long id) {
    }
}

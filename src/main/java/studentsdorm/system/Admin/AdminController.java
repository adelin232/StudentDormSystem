package studentsdorm.system.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/check-admin")
    public ResponseEntity<?> someProtectedAction(@RequestParam String userId) {
        if (adminService.isAdmin(userId)) {
            return ResponseEntity.ok().body("Acțiunea a fost efectuată.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acces interzis.");
        }
    }
}

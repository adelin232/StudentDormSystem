package studentsdorm.platform.Admin;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdminService {
    private final Set<String> adminUserIds = Set.of("aui97admsGYxIRrfv6G4OfBnJxk2");

    public boolean isAdmin(String userId) {
        return adminUserIds.contains(userId);
    }
}

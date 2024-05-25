package studentsdorm.system.Admin;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdminService {
    private final Set<String> adminUserIds = Set.of("CeUSCXcT3bZmSNZ0LXdu15HKXvw2");

    public boolean isAdmin(String userId) {
        return adminUserIds.contains(userId);
    }
}

package studentsdorm.platform.Admin;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdminService {
    private final Set<String> adminUserIds = Set.of("I5emXUU7QiM6pDtjrhxXUHYn7N73");

    public boolean isAdmin(String userId) {
        return adminUserIds.contains(userId);
    }
}

package Assignment_12_Proxy.service;

import java.util.HashSet;
import java.util.Set;

public class AccessControlService {

    private static AccessControlService instance;

    private Set<String> permissions;

    private AccessControlService() {
        permissions = new HashSet<>();
    }

    public static AccessControlService getInstance() {
        if (instance == null) {
            instance = new AccessControlService();
        }
        return instance;
    }

    public void grantAccess(String documentId, String username) {
        String permission = username + ":" + documentId;
        permissions.add(permission);
    }

    public boolean isAllowed(String documentId, String username) {

        String permission = username + ":" + documentId;

        return permissions.contains(permission);
    }
}

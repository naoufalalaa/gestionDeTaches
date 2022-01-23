package metier;

public class AuthenticatedUser {
    static private User authenticateduser;

    public static User getAuthenticateduser() {
        return authenticateduser;
    }

    private boolean isResponsable() {
        return authenticateduser.getROLE().equals("Responsable");
    }

    public static void setAuthenticateduser(User authenticateduser) {
        AuthenticatedUser.authenticateduser = authenticateduser;
    }
}

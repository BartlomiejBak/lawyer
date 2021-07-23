package pl.bartekbak.lawyer.entity;

public enum UserRole {
    CONTACT("contact"),
    PLAINTIFF("plaintiff"),
    DEFENDANT("defendant");

    public final String value;

    UserRole(String value) {
        this.value = value;
    }
}

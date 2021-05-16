package com.company;

/**
 * Store user's database information
 * "Test" is the name of the database
 * "root" is the username
 * "dlx990330" is the password
 * @author Carter Du
 */
public enum DatabaseInfo {
    USER("root"),
    DB_URL("jdbc:mysql://localhost:3306/Test"),
    PASSWORD("dlx990330")
    ;
    private final String info;

    DatabaseInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return info;
    }
}

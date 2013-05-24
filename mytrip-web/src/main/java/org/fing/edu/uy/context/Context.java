package org.fing.edu.uy.context;

/**
 *
 * @author JORGE
 */
public class Context {
    
    private static final ThreadLocal<UserContext> contextUser = new ThreadLocal<UserContext>();
    
    public static void set(UserContext user) {
        contextUser.set(user);
    }

    public static void unset() {
        contextUser.remove();
    }

    public static UserContext get() {
        return contextUser.get();
    }
    
}

package org.halo.thallo.common.web;

/**
 * Create At  2020/1/7 15:33
 *
 * @author: Lh
 * @version: 1.0.0
 */
public class UserContextHolder {
    private static final UserContextHolder instance = new UserContextHolder();

    private ThreadLocal<UserContext> threadLocal;

    private UserContextHolder() {
        this.threadLocal = new ThreadLocal<>();
    }

    public static UserContextHolder getInstance() {
        return instance;
    }

    public UserContext getContext() {
        return threadLocal.get();
    }

    public void setContext(UserContext context) {
        threadLocal.set(context);
    }
    /**
     * 清空上下文
     */
    public void clear() {
        threadLocal.remove();
    }
}

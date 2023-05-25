package Service;

public interface UserLock {

    boolean lockUser(int id);

    boolean unlockUser(int id);
}
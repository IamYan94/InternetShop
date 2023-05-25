package Model;
import java.util.ArrayList;

public class UserManager {

    private static ArrayList<User> users;
    private static ArrayList<User> loggedInUsers;

    static {
        users = new ArrayList<>();
        loggedInUsers = new ArrayList<>();
    }

    public static User createClient(String firstName, String lastName, String email, String password, double budget) {
        User newUser = new Client(firstName, lastName, email, password, budget);
        return validateUser(newUser);
    }

    public static User createAdmin(String firstName, String lastName, String email, String password) {
        User newUser = new Admin(firstName, lastName, email, password);
        return validateUser(newUser);
    }


    private static User validateUser(User user) {
        if (!checkUserPresence(user))
            users.add(user);
        else
            return null;
        return user;
    }

    public static boolean lockUser(User lockedBy, int id) {
        User userToLock = findUserById(id);
        if (userToLock == null) {
            System.out.println("Не вдається знайти користувача з таким ідентифікатором...");
            return false;
        }

        if (userToLock.status == UserStatus.ACTIVE) {
            userToLock.setStatus(UserStatus.BLOCKED);
            System.out.println("Користувач №: " + userToLock.getId() + " Заблоковано: " + lockedBy.getUserFullName() +
                    "-" + lockedBy.getStatus());
            return true;
        } else
            System.out.println(userToLock.getUserFullName() + " вже заблоковано");
        return false;
    }

    public static boolean unlockUser(User unlockedBy, int id) {
        User userToLock = findUserById(id);
        if (userToLock == null) {
            System.out.println("Не вдається знайти користувача з таким ідентифікатором...");
            return false;
        }
        if (userToLock.status == UserStatus.BLOCKED) {
            userToLock.setStatus(UserStatus.ACTIVE);
            System.out.println("Користувач №: " + userToLock.getId() + " розблоковано: " + unlockedBy.getUserFullName() +
                    "-" + unlockedBy.getStatus());
            return true;
        } else
            System.out.println(userToLock.getUserFullName() + " не заблокований ");
        return false;
    }

    public static boolean isLoggedIn(User user) {
        for (User usr : loggedInUsers) {
            if (user.equals(usr))
                return true;
        }
        return false;
    }

    static boolean checkUserPresence(User user) {

        for (User usr : users) {
            if (usr.equals(user))
                return true;
        }
        return false;
    }

    public static User findUserById(Integer id) {

        for (User usr : users) {
            if (usr.getId().equals(id))
                return usr;
        }
        return null;
    }

    public static User findUserByEmail(String login) {
        for (User usr : users) {
            if (usr.getEmail().equals(login))
                return usr;
        }
        return null;
    }

    public static void displayAllUsers() {
        for (User usr : users) {
            System.out.println(usr);
        }
    }

    public static ArrayList<User> getAllUsers() {
        return users;
    }

    public static ArrayList<User> getLoggedInUsers() {
        return loggedInUsers;
    }

    public static int getUsersAmount() {
        return users.size();
    }

    public static int getLoggedInUsersAmount() {
        return loggedInUsers.size();
    }


}
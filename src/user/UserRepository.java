package user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository {

    private static List<User> userList;
    // currentUser 의 경우 static 으로 설정하게 되면 전체적으로 공유가 되기때문에
    // 여러명이 로그인 할 수 없고 한 사용자만 로그인이 가능함
    private User currentUser;
    private static List<User> admin;


    static {
        userList = new ArrayList<>();
        admin = new ArrayList<>();
        admin.add(new User("admin", "admin", "1q2w3e4r!", 0, "admin"));
        userList.add(new User("김주성", "1234", "12345", 28, "1231231234"));
        userList.add(new User("박성진", "2345", "23456", 24, "2342342345"));
        userList.add(new User("정재한", "3456", "34567", 27, "2342342346"));
        userList.get(0).setMoney(34000000);
        userList.get(1).setMoney(23050);
        userList.get(2).setMoney(60000);
    }


    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "UserRepository{" + "userList=" + userList + '}';
    }

    public User checkId(String id) {
        List<User> checkedId = userList.stream().filter(user -> user.getId().equals(id)).collect(Collectors.toList());
        if (checkedId.size() == 1) {
            return checkedId.get(0);
        }
        return null;
    }

    public User checkPassword(User user, String password) {
        if (user.getPassword().equals(password)) return user;
        return null;
    }

    public void addUser(String name, String id, String password, int age) {
        String userAccount;
        while (true) {
            int randomNumber = (int) Math.floor(Math.random() * 1000000);
            String randomAccount = "602" + Integer.toString(randomNumber);

            boolean flag = userList.stream().anyMatch(user -> user.getAccount().equals(randomAccount));
            if (!flag) {
                userAccount = randomAccount;
                break;
            }
        }
        userList.add(new User(name, id, password, age, userAccount));
    }


    public User findById(String inputId) {

        for (User user : userList) {
            if (user.getId().equals(inputId)) {
                return user;
            }
        }
        return null;

    }


    /**
     * 사용자 정보를 업데이트하는 메서드.
     * 주어진 사용자 정보와 동일한 ID를 가진 사용자를 찾아 업데이트.
     *
     * @param user 업데이트할 사용자 객체
     **/


    public void removeUser(User user) {
        userList.remove(user);
    }

    public User searchUser(String userInput) {
        for (User user : userList) {
            if (user.getId().equals(userInput) || user.getName().equals(userInput)) {
                return user;
            }
        }
        return null;
    }

    public User findByPassword(String inputPassword) {
        for (User user : userList) {
            if (user.getPassword().equals(inputPassword)) {

                return user;
            }
        }
        return null;
    }

    public User checkAdmin(String id) {
        List<User> checkedId = admin
                .stream()
                .filter(user -> user.getId().equals(id))
                .collect(Collectors.toList());
        if (checkedId.size() == 1) {
            return checkedId.get(0);
        }
        return null;

    }

    public User checkAdminPassword(User user, String password) {
        if (user.getPassword().equals(password)) return user;
        return null;
    }

    public void deleteUser(User user) {
        userList.remove(user);
    }
}



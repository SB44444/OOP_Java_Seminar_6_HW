package notebook.controller;

import notebook.model.User;
import notebook.model.repository.GBRepository;

import java.util.List;
import java.util.Objects;

public class UserController<C> {
    private final GBRepository<User, Long> repository;
    private long id;


    public UserController(GBRepository<User, Long> repository) {
        this.repository = repository;
    }
    public void saveUser(User user) { repository.create(user); }
    public User readUser(Long userId) {
        List<User> users = repository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                return user;
            }
        }
        throw new RuntimeException("User not found");
    }
    public User findUserById(long id) {
        this.id = id;
        return repository.findById(id).orElseThrow(()-> new RuntimeException("User not found."));  }
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public void deleteUser(long userId) {
        Long id=Long.parseLong(String.valueOf(userId));
        repository.delete(id);
    }
    public void updateUser(String userId, User update) {
        update.setId(Long.parseLong(userId));
        repository.update(Long.parseLong(userId), update);
    }
}

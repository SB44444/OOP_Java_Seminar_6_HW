package notebook.model.repository;

import notebook.model.User;

import java.util.List;
import java.util.Optional;


public interface GBRepository<U, L extends Number> {
    List<User> findAll();
    User create(User user);
    Optional<User> findById(Long ignoredId);
    Optional<User> update(Long userId, User update);
    boolean delete(Long id);

    abstract void write(List<User> users);

}

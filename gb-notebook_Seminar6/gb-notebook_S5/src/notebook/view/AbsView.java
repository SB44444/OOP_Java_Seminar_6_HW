package notebook.view;

import notebook.controller.UserController;

public abstract class AbsView<User> {
    public UserController<User> userController;
    public abstract void run();
}


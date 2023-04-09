package notebook.util;

import notebook.view.mapper.C;
import notebook.controller.UserController;
import notebook.model.User;
import notebook.model.dao.impl.FileOperation;
import notebook.model.repository.GBRepository;
import notebook.model.repository.impl.UserRepository;
import notebook.view.UserView;
import static notebook.util.DBConnector.DB_PATH;
public class AvtoRun {
    private FileOperation fileOperation;
    private GBRepository<User, Long> repository;
    private UserController<C> controller;
    UserView view;
    public AvtoRun() {
        FileOperation fileOperation = new FileOperation(DB_PATH);
        GBRepository<User, Long> repository = new UserRepository(fileOperation);
        UserController<C> controller = new UserController<C>(repository);
        UserView view = new UserView(controller);
        view.run();
    }
    public void run() {
        DBConnector.createDB();
        view.run();
    }
}

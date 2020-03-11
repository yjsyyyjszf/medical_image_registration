package medicalimagebrowser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import medicalimagebrowser.entity.User;
import medicalimagebrowser.model.ResponseModel;
import medicalimagebrowser.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/list")
    public String view() {
        return "user";
    }

    @GetMapping
    @ResponseBody
    public ResponseModel<User> userList() {
        ResponseModel<User> responseModel = new ResponseModel<>();
        List<User> users = userRepository.findAll();
        responseModel.setRows(users);
        responseModel.setTotal(users.size());
        return responseModel;
    }

}


package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import persistence.User;
import service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("login") String login) {
        return userService.getUserByLogin(login);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public boolean insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    @RequestMapping(value = "/{login}", method = RequestMethod.PUT)
    public boolean updateUser(@PathVariable("login") String login, @RequestBody User user) {
        return userService.updateUser(login, user);
    }

    @RequestMapping(value = "/{login}", method = RequestMethod.DELETE)
    public boolean deleteUser(@PathVariable("login") String login) {
        return userService.deleteUser(login);
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<String> getUsers() {
        return userService.getUsers();
    }
}

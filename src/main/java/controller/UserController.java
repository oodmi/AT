package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import persistence.Book;
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
    public void insertUser(@RequestBody User user) {
        userService.insertUser(user);
    }

    @RequestMapping(value = "/{login}", method = RequestMethod.PUT)
    public void updateUser(@PathVariable("login") String login, @RequestBody User user) {
        userService.updateUser(user);
    }

    @RequestMapping(value = "/{login}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("login") String login) {
        userService.deleteUser(login);
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userService.getUsers();
    }
}

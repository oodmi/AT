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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") Long id) {
        User userById = userService.getUserById(id);
        return userById;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public boolean insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUsers() {
        List<User> users = userService.getUsers();
        return users;
    }
}
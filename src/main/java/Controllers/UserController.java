package Controllers;

import Model.User;
import Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(User user) {
        userService.register(user);
        return "redirect:/login";
    }

    @GetMapping("/profile/{username}")
    public String profile(@PathVariable String username, Model model) {
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/update")
    public String update(User user) {
        userService.update(user);
        return "redirect:/profile/" + user.getUsername();
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        userService.delete(id);
        return "redirect:/login";
    }
}
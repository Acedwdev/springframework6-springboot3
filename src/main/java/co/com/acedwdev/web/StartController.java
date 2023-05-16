package co.com.acedwdev.web;

import co.com.acedwdev.domain.User;
import co.com.acedwdev.service.ServiceUser;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class StartController {

    @Autowired
    private ServiceUser serviceUser;

    @GetMapping("/")
//     public String start(Model model, @AuthenticationPrincipal User user)
    public String start(Model model) {
        var users = serviceUser.userList();

        log.info("Run Spring MVC Controller");
//        log.info("User login: " + user);

        model.addAttribute("users", users);
        var totalBalance = 0D;
        for (var u : users) {
            totalBalance += u.getAmount();
        }
        model.addAttribute("totalBalance", totalBalance);
        model.addAttribute("totalCustomers", users.size());
        return "index";

    }

    @GetMapping("/add")
    public String add(User user) {
        return "modify";
    }

    @PostMapping("/save")
    public String save(@Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return "modify";
        }
        serviceUser.save(user);
        return "redirect:/";
    }

    @GetMapping("/edit/{idUser}")
    public String edit(User user, Model model) {
        user = serviceUser.findUser(user);
        model.addAttribute("user", user);
        return "modify";
    }

    /*@GetMapping("/delete/{idUser}")
    public String delete(User user){
        serviceUser.delete(user);
        return "redirect:/";
    }*/
    @GetMapping("/delete")
    public String delete(User user) {
        serviceUser.delete(user);
        return "redirect:/";
    }

}

package com.woniuxy.mall.portal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.mall.commons.MailMsg;
import com.woniuxy.mall.entity.User;
import com.woniuxy.mall.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Resource
    private UserService userService;

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @PostMapping("register")
    public String register(User user) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("447641244@qq.com");
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("用户注册激活邮件");
        simpleMailMessage.setText("<a href='http://localhost:8080/active?id=1'></a>");
        mailSender.send(simpleMailMessage);
        user.setScore(0);
        user.setIsDel("n");
        user.setRegtime(LocalDateTime.now());
        user.setStatus("n");
        userService.save(user);
        MailMsg mailMsg = new MailMsg();
        mailMsg.setId(1);
        mailMsg.setEmail(user.getEmail());
        amqpTemplate.convertAndSend("mail_exchange", "mail", mailMsg);
        return "login";
    }

    @GetMapping("active")
    public String active(Integer id, String token) {
        User user = new User();
        user.setId(id);
        user.setStatus("y");
        userService.updateById(user);
        return "login";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("login")
    public String login(String account, String password, Model model, HttpSession session) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        User user = userService.getOne(queryWrapper);
        if (user == null) {
            model.addAttribute("msg", "账号或密码不正确");
        } else if (user.getStatus().equals("n")) {
            model.addAttribute("msg", "账号未激活");
        } else if (!user.getPassword().equals(password)) {
            model.addAttribute("msg", "账号密码不正确");
        } else {
            session.setAttribute("user", user);
        }
        return "redirect:/";
    }

}

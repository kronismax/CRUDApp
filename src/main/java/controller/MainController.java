package controller;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    Session session = null;

    @RequestMapping("/")
    public String setupForm(Map<String, Object> map) {
        User user = new User();
        map.put("user", user);
        map.put("userList", getAllUser());
        return "user";
    }

    @RequestMapping(value = "/user.do", method = RequestMethod.POST)
    public String doActions(@ModelAttribute User user, BindingResult result, @RequestParam String action,
                            Map<String, Object> map) {
        User userResult = new User();

        String s = action.toLowerCase();
        if (s.equals("add")) {
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.save(user);
                session.getTransaction().commit();
            } catch (Exception e) {
            }
            userResult = user;

        } else if (s.equals("edit")) {
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.update(user);
                session.getTransaction().commit();
            } catch (Exception e) {
            }
            userResult = user;

        } else if (s.equals("delete")) {
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.delete(user);
                session.getTransaction().commit();
            } catch (Exception e) {
            }
            userResult = new User();

        } else if (s.equals("search by id")) {
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.refresh(user);
                session.contains(user);
                session.getTransaction().commit();
            } catch (Exception e) {
            }
            userResult = user;
        }
        map.put("user", userResult);
        map.put("userList", getAllUser());
        return "user";
    }

    public List getAllUser() {
        List<User> tmp = new ArrayList<User>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tmp = session.createCriteria(User.class).list();
        } catch (Exception e) {
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }
        }
        return tmp;
    }
}
package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.Model.A_Models;
import com.cards.shvedko.Model.Users;
import org.hibernate.HibernateException;

import java.util.Objects;

/**
 * Created by hennadii.shvedko on 14/07/2017.
 */
public class UsersDAO extends ModelsDAO {

    public Users user;

    public static final String USERNAME_ERROR = "username_error";
    public static final String USERNAME_EMPTY = "username_empty";
    public static final String PASSWORD_ERROR = "password_error";
    public static final String AUTHENTICATION_FAILED = "authentication_failed";
    public static final String AUTHENTICATION_OK = "authentication_success";

    public UsersDAO() {
        super();
        user = new Users();
    }

    public UsersDAO(int id) {
        super();
        user = (Users) session.get(Users.class, id);
    }

    public Users getUserById(int id) throws HibernateException {
        return (Users) session.get(Users.class, id);
    }

    public Users getUserByUsername(String username) throws HibernateException {
        return (Users) session.get(Users.class, username);
    }

    public static String authenticator(String loginValue, String passwordValue) {
        UsersDAO usersDAO = new UsersDAO();
        A_Models userObject = null;

        if(loginValue == null || loginValue.isEmpty()){
            return USERNAME_EMPTY;
        }

        try {
            userObject = usersDAO.select("where name='" + loginValue + "'");
        } catch (Exception e) {
            e.printStackTrace();
            return AUTHENTICATION_FAILED;
        }

        if(userObject == null){
            return USERNAME_ERROR;
        }
        if(!Objects.equals(userObject.getPassword(), passwordValue)){
            return PASSWORD_ERROR;
        }

        A_Controller.globalUserModel = userObject;
        return AUTHENTICATION_OK;
    }

    public boolean save() throws Exception {
        if (errorMsg.equals("")) {
            session.persist(user);
            transaction.commit();
            session.close();
        } else{
            return false;
        }
        return true;
    }
}

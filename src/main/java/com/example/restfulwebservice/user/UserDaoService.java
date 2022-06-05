package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private  static final List<User2> USER_2s = new ArrayList<>();

    private static int usersCount = 3;
    static{
        USER_2s.add(new User2(1,"kennth", new Date(),"pass1","701010-1111111"));
        USER_2s.add(new User2(2,"Alice", new Date(),"pass2","801010-2222222"));
        USER_2s.add(new User2(3,"Elena", new Date(),"pass3","901010-1111111"));
    }

    public List<User2> findAll() {
        return USER_2s;
    }

    public User2 save(User2 user2){
        if(user2.getId()==null){
            user2.setId(++usersCount);
        }
        USER_2s.add(user2);
        return user2;
    }

    public User2 findOne(int id){
        for (User2 user2 : USER_2s){
            if(user2.getId() == id){
                return user2;
            }
        }
        return null;
    }

    public User2 deleteById(int id) {
        Iterator<User2> iterator = USER_2s.iterator();

        while (iterator.hasNext()) {
            User2 user2 = iterator.next();

            if (user2.getId() == id) {
                iterator.remove();
                return user2;
            }
        }
        return null;
    }
}

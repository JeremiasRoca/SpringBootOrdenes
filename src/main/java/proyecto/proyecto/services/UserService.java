package proyecto.proyecto.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import proyecto.proyecto.models.MyUser;
import proyecto.proyecto.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository ur;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> optional = ur.findByUsername(username);

        if (optional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        else{
            MyUser u = optional.get();
            Set<GrantedAuthority> set = new HashSet<>();
            set.add(new SimpleGrantedAuthority(ur.getRole(username)));
           return new User(u.getUsername(), u.getPassword(), set);
        }

    }

    public boolean save(MyUser u )
    {
        if(ur.getById(u.getUsername()) == null) {
            ur.save(u);
            return true;
        }
        else
        {
            return  false;
        }

    }



    public List<MyUser> getAll()
    {
        return ur.findAll();

    }

    public boolean update(MyUser u, String email) {
        u.setUsername(email);
        if(ur.getById(u.getUsername()) != null) {
            ur.save(u);
            return true;
        }
        else
        {
            return  false;
        }

    }
    public boolean delete(String email) {

        try {
            ur.deleteById(email);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public MyUser findByUsername(String username)
    {
        Optional<MyUser> u = ur.findByUsername(username);
        if(!u.isEmpty())
        {
            return u.get();
        }
        else
        return null;

    }
    
}

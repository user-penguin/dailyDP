package reserve.control.services;

import connect.RemoteConnection;

import lombok.NonNull;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import reserve.control.persistence.UserDao;
import reserve.control.domain.User;

import javax.annotation.PostConstruct;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @PostConstruct
    public void init() throws Exception{
        Registry registry = LocateRegistry.getRegistry("localhost", 2021);
        RemoteConnection service = (RemoteConnection) registry.lookup("adm/ConnectService");

        JSONArray jArrWithUsers = new JSONArray(service.getUsers());

        for(int i = 0; i < jArrWithUsers.length(); i++) {
            JSONObject systemUser = jArrWithUsers.getJSONObject(i);
            if (!userDao.findByUsername(systemUser.getString("login")).isPresent()) {
                userDao.save(User.builder()
                        .username(systemUser.getString("login"))
                        .authorities(Tools.getRoles(Integer.parseInt(systemUser.getString("idTypeAccount"))))
                        .password(new BCryptPasswordEncoder().encode(systemUser.getString("password")))
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .enabled(true)
                        .build());
            }
        }

    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("user " + username + " was not found!"));
    }
}
package proyecto.proyecto.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.proyecto.models.MyUser;
import proyecto.proyecto.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService uS;

    @GetMapping
    public ResponseEntity getAll()
    {
        List<MyUser> users = uS.getAll();
        if(!users.isEmpty())
        {
           return ResponseEntity.status(200).body(users);
        }
        else {
            return ResponseEntity.status(204).body(users);
        }
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody MyUser user)
    {
        boolean flag = uS.save(user);
        if(flag)
            return  ResponseEntity.status(200).body("Success.");
        else
            return  ResponseEntity.status(400).body("Error.");
    }

    @PutMapping("/update/{email}")
    public ResponseEntity update(@RequestBody MyUser user, @PathVariable("email") String email)
    {
        boolean flag = uS.update(user, email);
        if(flag)
            return  ResponseEntity.status(200).body("Success.");
        else
            return  ResponseEntity.status(400).body("Error.");
    }

}

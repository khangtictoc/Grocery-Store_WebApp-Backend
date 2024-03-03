package com.cybersoft.grocerystore.app.role.controller;

import com.cybersoft.grocerystore.app.role.entity.RoleEntity;
import com.cybersoft.grocerystore.app.role.service.Imp.RoleServiceImp;
import com.cybersoft.grocerystore.libraries.payload.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleServiceImp roleServiceImp;

    @CrossOrigin
    @PostMapping("add")
    public ResponseEntity<?> addRole (@RequestParam String name, @RequestParam String description){
        roleServiceImp.addRole(name,description);
        return new ResponseEntity<>("Insert role successfully", HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("deletebyid")
    public ResponseEntity<?> deleteRole (@RequestParam int id){
        roleServiceImp.deleteRole(id);
        return new ResponseEntity<>("Delete role successfully",HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("updatebyid")
    public ResponseEntity<?> updateRole (@RequestParam int id, @RequestParam String name, @RequestParam(required = false) String description){
        roleServiceImp.updateRole(id,name,description);
        return  new ResponseEntity<>("Update role successfully",HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("getall")
    public ResponseEntity<?> getAllRole(HttpServletRequest request){
        List<RoleEntity> listRoles = roleServiceImp.findAllRole();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(listRoles);

        return  new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
}

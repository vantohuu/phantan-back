package com.example.demo.controllers;

import com.example.demo.filter.QueueClass;
import com.example.demo.models.CTVe;
import com.example.demo.models.ResponseObject;
import com.example.demo.models.User;
import com.example.demo.models.Ve;
import com.example.demo.repositories.CTVeReponsitory;
import com.example.demo.repositories.UserReponsitory;
import com.example.demo.repositories.VeReponsitory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "ve/")
public class VeController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserReponsitory userReponsitory;
    @Autowired
    private VeReponsitory veRepository;

    @Autowired
    private CTVeReponsitory ctveRepository;


    @RequestMapping("/getall-ve")
    List<Ve> getAllVe() {
        return veRepository.findAll();
    }

    @RequestMapping("/getall-ctve")
    List<CTVe> getAllCtVe() {
        return ctveRepository.findAll();
    }

    @RequestMapping("/getall-order")
    List<CTVe> getAllOderVe() {
        return ctveRepository.getAllOrderVe();
    }
//    @RequestMapping(value = "/order-ticket",method = RequestMethod.OPTIONS)
//    public ResponseEntity handle() {
//        return new ResponseEntity(HttpStatus.OK);
//    }
    @PutMapping("/order-ticket")
    ResponseEntity<ResponseObject> putVe(@RequestBody CTVe newctVe) {
//        while (QueueClass.isFlag()) {
////            System.out.println(QueueClass.isFlag());
//        }
        while (QueueClass.isFlag() || (QueueClass.getPriorityUser() != null && !QueueClass.getPriorityUser().equals(newctVe.getUsername()))) {
            System.out.println(QueueClass.getPriorityUser() + "   " + newctVe.getUsername());
        }
        System.out.println("Begin order: " + QueueClass.getPriorityUser());
        QueueClass.setFlag(true);
        System.out.println(newctVe.toString());
        Optional<Ve> foundVe = veRepository.findById(newctVe.getIDVE());
        if (foundVe.isPresent() && foundVe.get().getStatus() == 1) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Removed user (order failed): "  + QueueClass.removeUser() + "\n foundVe:" + foundVe );
            QueueClass.setFlag(false);
            PrintQueueUser();
            System.out.println("Flag: " + QueueClass.isFlag() + "\n");

            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "ticket is ordered", newctVe)
            );
        }
        newctVe.setCreate_at(new Date());
        Ve newVe = new Ve(foundVe.get().getId(),1);
        ctveRepository.save(newctVe);
        veRepository.save(newVe);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n Removed user (order success): "  + QueueClass.removeUser());
        QueueClass.setFlag(false);
        PrintQueueUser();
        System.out.println("Flag: " + QueueClass.isFlag() + "\n");
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "update detail ticket successfully", newctVe)
        );
    }
    @PutMapping("/update-ticket")
    ResponseEntity<ResponseObject> putCTVe(@RequestBody Ve newVe) {
        System.out.println(newVe.toString());
        veRepository.save(newVe);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "update ticket successfully", newVe)
        );
    }

    void PrintQueueUser()
    {
           int count = 0;
            for (String i : QueueClass.getUserQueue()) {
                count++;
                System.out.println(count + ".username:" + i);
            }
    }

}

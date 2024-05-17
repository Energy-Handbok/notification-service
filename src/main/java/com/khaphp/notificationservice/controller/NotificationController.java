package com.khaphp.notificationservice.controller;

import com.khaphp.common.dto.ResponseObject;
import com.khaphp.notificationservice.dto.NotificationDTOcreate;
import com.khaphp.notificationservice.dto.NotificationDTOupdate;
import com.khaphp.notificationservice.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/notification")
//@SecurityRequirement(name = "EnergyHandbook")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(defaultValue = "10") int pageSize,
                                    @RequestParam(defaultValue = "1") int pageIndex,
                                    @RequestParam(defaultValue = "") String userId){
        ResponseObject<Object> responseObject = notificationService.getAll(pageSize, pageIndex, userId);
        if(responseObject.getCode() == 200){
            return ResponseEntity.ok(responseObject);
        }
        return ResponseEntity.badRequest().body(responseObject);
    }
    @GetMapping("/detail")
    public ResponseEntity<Object> getObject(String id){
        ResponseObject<Object> responseObject = notificationService.getDetail(id);
        if(responseObject.getCode() == 200){
            return ResponseEntity.ok(responseObject);
        }
        return ResponseEntity.badRequest().body(responseObject);
    }

    @PostMapping
    public ResponseEntity<Object> createObject(@RequestBody @Valid NotificationDTOcreate object){
        ResponseObject<Object> responseObject = notificationService.create(object);
        if(responseObject.getCode() == 200){
            return ResponseEntity.ok(responseObject);
        }
        return ResponseEntity.badRequest().body(responseObject);
    }

    @PutMapping
    public ResponseEntity<Object> updateObject(@RequestBody @Valid NotificationDTOupdate object){
        ResponseObject<Object> responseObject = notificationService.update(object);
        if(responseObject.getCode() == 200){
            return ResponseEntity.ok(responseObject);
        }
        return ResponseEntity.badRequest().body(responseObject);
    }

    @PutMapping("/seen/{notiId}")
    public ResponseEntity<Object> updateObjectSeen(@PathVariable String notiId){
        ResponseObject<Object> responseObject = notificationService.updateSeen(notiId);
        if(responseObject.getCode() == 200){
            return ResponseEntity.ok(responseObject);
        }
        return ResponseEntity.badRequest().body(responseObject);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteObject(String id){
        ResponseObject<Object> responseObject = notificationService.delete(id);
        if(responseObject.getCode() == 200){
            return ResponseEntity.ok(responseObject);
        }
        return ResponseEntity.badRequest().body(responseObject);
    }
}

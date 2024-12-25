package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Announcement;
import org.example.service.AnnouncementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ads")
public class AppController {

    private final AnnouncementService announcementService;

    @GetMapping
    public List<Announcement> findALl(){
        return announcementService.findAll();
    }

    @GetMapping("/{category}")
    public List<Announcement> findByCategory(@PathVariable String category){
        return announcementService.findByCategory(category);
    }

    @GetMapping("/id/{id}")
    public List<Announcement> findById(@PathVariable UUID id){
        return announcementService.findById(id);
    }

    @PutMapping("/{id}/stock")
    public void updateStock(@PathVariable UUID id, @RequestParam float stock) {
        announcementService.updateStock(id, stock);
    }
}

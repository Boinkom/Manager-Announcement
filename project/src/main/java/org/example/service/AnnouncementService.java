package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Announcement;
import org.example.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public List<Announcement> findAll() {
        return announcementRepository.findAll();
    }

    public List<Announcement> findByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category must not be empty");
        }
        return announcementRepository.findByCategory(category);
    }

    public List<Announcement> findById(UUID id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must not be null");
        }
        List<Announcement> announcement = announcementRepository.findById(id);

        if (announcement.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcement with id " + id + " not found");
        }
        return announcement;
    }


    public void updateStock(UUID id, float stock) {
        if (stock < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock value cannot be negative");
        }

        List<Announcement> announcement = announcementRepository.findById(id);

        if (announcement.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcement with id " + id + " not found");
        }

        announcementRepository.updateInStockById(id, stock);
    }
}

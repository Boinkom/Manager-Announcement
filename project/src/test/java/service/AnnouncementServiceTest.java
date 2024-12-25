package service;

import org.example.entity.Announcement;
import org.example.repository.AnnouncementRepository;
import org.example.service.AnnouncementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementServiceTest {

    @Mock
    private AnnouncementRepository announcementRepository;

    @InjectMocks
    private AnnouncementService announcementService;

    private Announcement announcement;
    private UUID announcementId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        announcementId = UUID.randomUUID();
        announcement = new Announcement();
        announcement.setId(announcementId);
        announcement.setCategory("Technology");
        announcement.setInStock(100);
    }

    @Test
    void testFindAll() {
        List<Announcement> announcements = Collections.singletonList(announcement);
        when(announcementRepository.findAll()).thenReturn(announcements);

        List<Announcement> result = announcementService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(announcementId, result.get(0).getId());
        verify(announcementRepository, times(1)).findAll();
    }

    @Test
    void testFindByCategory() {
        String category = "Technology";
        List<Announcement> announcements = Collections.singletonList(announcement);
        when(announcementRepository.findByCategory(category)).thenReturn(announcements);

        List<Announcement> result = announcementService.findByCategory(category);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Technology", result.get(0).getCategory());
        verify(announcementRepository, times(1)).findByCategory(category);
    }

    @Test
    void testFindById() {

        List<Announcement> announcements = Collections.singletonList(announcement);
        when(announcementRepository.findById(announcementId)).thenReturn(announcements);

        List<Announcement> result = announcementService.findById(announcementId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(announcementId, result.get(0).getId());
        verify(announcementRepository, times(1)).findById(announcementId);
    }

    @Test
    public void testUpdateStock() {
        UUID id = UUID.randomUUID();
        Announcement announcement = new Announcement();
        announcement.setId(id);
        announcement.setCategory("Metal");
        announcement.setInStock(100);

        when(announcementRepository.findById(id)).thenReturn(List.of(announcement));

        float newStock = 150;
        announcementService.updateStock(id, newStock);

        verify(announcementRepository).updateInStockById(id, newStock);
    }
}

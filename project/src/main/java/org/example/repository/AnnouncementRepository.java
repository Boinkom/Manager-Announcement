package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    List<Announcement> findByCategory(String category);

    List<Announcement> findById(UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE Announcement a SET a.inStock = :stock, a.updateDate = CURRENT_DATE WHERE a.id = :id")
    void updateInStockById(@Param("id") UUID id, @Param("stock") float stock);
}

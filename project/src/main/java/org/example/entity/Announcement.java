package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "ad")
public class Announcement {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 255)
    private String category;

    @Column(nullable = false, length = 255)
    private String standard;

    @Column(nullable = false, length = 255)
    private String grade;

    @Column(nullable = false, length = 255)
    private String size;

    @Column(nullable = false, length = 255)
    private String provider;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false)
    private int inStock;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date updateDate;

}

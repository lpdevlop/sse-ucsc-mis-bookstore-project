package com.ucsc.bookstoreproject.database.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.Instant;

@Entity
@Table(name = "login_attempt")
@EntityListeners(AuditingEntityListener.class)
public class LoginAttempt {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private int attempts;

    @CreationTimestamp
    private Instant lastAttempt;


}

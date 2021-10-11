package com.codenation.group3.centralDeErros.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "errors_table")
public class ErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 100, nullable = false)
    private String level;

    @NotNull
    @Column(length = 255, nullable = false)
    private String description;

    @NotNull
    @Column(length = 500, nullable = false)
    private String log;

    @NotNull
    @Column(length = 100, nullable = false)
    private String origin;

    @NotNull
    @Column(length = 255, nullable = false, name = "created_at")
    private LocalDateTime createdAt;


}

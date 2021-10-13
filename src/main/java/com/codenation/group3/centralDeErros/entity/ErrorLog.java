package com.codenation.group3.centralDeErros.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
//@EntityListeners(AuditingEntityListener.class)
@Table(name = "errors_table")
public class ErrorLog extends Auditable<String>{

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

//    @NotNull
//    @Column(length = 255, nullable = false, name = "created_at")
//    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ErrorLog errorLog = (ErrorLog) o;
        return id != null && Objects.equals(id, errorLog.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

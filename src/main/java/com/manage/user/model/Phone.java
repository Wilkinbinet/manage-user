package com.manage.user.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.UUID;

/**
 * Entity representing a phone in the database.
 */
@Data
@Entity
@Table(name = "phones")
public class Phone {

    /**
     * Unique identifier for the phone.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    /**
     * Phone number.
     */
    @Column(nullable = false)
    private String number;

    /**
     * City code of the phone.
     */
    @Column(name = "city_code", nullable = false)
    private String cityCode;

    /**
     * Country code of the phone.
     */
    @Column(name = "contry_code", nullable = false)
    private String countryCode;

    /**
     * User to whom the phone belongs.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

}

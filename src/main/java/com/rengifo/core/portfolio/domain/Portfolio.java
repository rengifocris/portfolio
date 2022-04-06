package com.rengifo.core.portfolio.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "portfolio")
@Data
@EqualsAndHashCode
public class Portfolio implements Serializable {

    private static final long serialVersionUID = 20200427L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idportfolio", nullable = false)
    private Long id;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @Column(name = "twitter_user_name", nullable = true)
    private String twitterUsername;

    @Column(name = "title", nullable = true)
    private String title;

}

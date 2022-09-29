package com.konasl.livescore.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "live_scores")
public class LiveScore extends BaseEntity{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    private String link;
    private String description;
    private String uri;
}
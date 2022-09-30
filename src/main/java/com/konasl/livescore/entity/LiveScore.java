package com.konasl.livescore.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(
        name = "live_scores",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "link", name = "uc_live_scores_link"),
                @UniqueConstraint(columnNames = "uri", name = "uc_live_scores_uri")
        }
)
public class LiveScore extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    private String link;
    private String description;
    private String uri;
}
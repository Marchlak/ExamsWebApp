package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "relation_23")
public class Relation23 {
    @EmbeddedId
    private Relation23Id id;

    @MapsId("groupClassid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "group_classid", nullable = false)
    private Group groupClassid;

    @MapsId("egzaminatorEgzaminatorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "egzaminator_egzaminator_id", nullable = false)
    private Egzaminator egzaminatorEgzaminator;

}
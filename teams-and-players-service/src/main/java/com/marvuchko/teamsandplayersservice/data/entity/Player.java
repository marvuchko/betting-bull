package com.marvuchko.teamsandplayersservice.data.entity;

import com.marvuchko.infrastructuremicroservice.data.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = Player.TABLE_NAME)
public class Player extends BaseEntity<Long> {

    public static final String TABLE_NAME = "player";

    @Column(name = "first_name", length = 120)
    private String firstName;

    @Column(name = "last_name", length = 120)
    private String lastName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "career_start_date")
    private Date careerStartDate;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private Team team;

}

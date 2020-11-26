package com.marvuchko.teamsandplayersservice.data.entity;

import com.marvuchko.infrastructuremicroservice.data.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = Team.TABLE_NAME)
public class Team extends BaseEntity<Long> {

    public static final String TABLE_NAME = "team";

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "preferred_currency", length = 3)
    private String preferredCurrency;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<Player> players;

}

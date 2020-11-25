package com.marvuchko.teamsandplayersservice.data.entity;

import com.marvuchko.infrastructuremicroservice.data.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @OneToMany(mappedBy = "team")
    private Set<Player> players;

}

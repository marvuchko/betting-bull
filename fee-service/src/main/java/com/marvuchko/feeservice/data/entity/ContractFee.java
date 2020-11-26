package com.marvuchko.feeservice.data.entity;

import com.marvuchko.infrastructuremicroservice.data.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = ContractFee.TABLE_NAME)
public class ContractFee extends BaseEntity<Long> {

    public static final String TABLE_NAME = "contract_fee";

    @Column(name = "id_team")
    private Long teamId;

    @Column(name = "id_player")
    private Long playerId;

    @Column(name = "transfer_fee")
    private Float transferFee;

    @Column(name = "team_commission")
    private Float teamCommission;

    @Column(name = "total_fee")
    private Float totalFee;

    @Column(name = "currency")
    private String currency;

}

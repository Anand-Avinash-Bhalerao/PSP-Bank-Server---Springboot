package com.billion_dollor_company.Bank_Server.models;

import com.billion_dollor_company.Bank_Server.util.Constants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = Constants.DatabaseTables.LISTKEYS_TABLE)
public class ListKeysInfo {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column
    private String publicKey;

    @Column
    private String privateKey;

}

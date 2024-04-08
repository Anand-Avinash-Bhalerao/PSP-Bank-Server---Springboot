package com.billion_dollor_company.Bank_Server.payloads.listKeys;

import com.billion_dollor_company.Bank_Server.models.projections.ListKeysInfoProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListKeysReqDTO {


    private Integer id;

    private String publicKey;

    public ListKeysReqDTO(ListKeysInfoProjection projection) {
        this.id = projection.getId();
        this.publicKey = projection.getPublicKey();
    }


}

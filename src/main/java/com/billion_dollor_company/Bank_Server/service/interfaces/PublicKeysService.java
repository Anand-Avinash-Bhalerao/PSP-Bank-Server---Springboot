package com.billion_dollor_company.Bank_Server.service.interfaces;

import com.billion_dollor_company.Bank_Server.payloads.listKeys.ListKeysReqDTO;

import java.util.List;


public interface PublicKeysService {
     List<ListKeysReqDTO> getListKeys();

}

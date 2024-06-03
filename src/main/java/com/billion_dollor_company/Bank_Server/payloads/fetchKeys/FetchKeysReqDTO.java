package com.billion_dollor_company.Bank_Server.payloads.fetchKeys;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JacksonXmlRootElement(localName = "response")
public class FetchKeysReqDTO {
}

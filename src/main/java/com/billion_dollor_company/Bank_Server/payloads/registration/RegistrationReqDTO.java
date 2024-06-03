package com.billion_dollor_company.Bank_Server.payloads.registration;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationReqDTO {

    @NotNull(message = "The encrypted text cannot be empty")
    private String encryptedText;

}

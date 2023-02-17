package com.yukon.logistics.configuration.security.authentication;


import com.yukon.logistics.common.ApplicationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
    
    @Size(min = ApplicationConstants.DataValidation.MIN_SIZE_OF_EMAIL,
            max = ApplicationConstants.DataValidation.MAX_SIZE_OF_EMAIL)
    @NotEmpty
    @NotBlank
    String email;
    
    @Size(min = ApplicationConstants.DataValidation.MIN_SIZE_OF_PASSWORD,
            max = ApplicationConstants.DataValidation.MAX_SIZE_OF_PASSWORD)
    @NotEmpty
    @NotBlank
    String password;
    
}

package com.icodeap.ecommerce.infrastructure.dto;
import com.icodeap.ecommerce.domain.User;
import com.icodeap.ecommerce.domain.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {

    private String userName;
    @NotBlank(message = "Nombre no valido")
    private String firstName;
    @NotBlank(message = "Apellido no valido")
    private String lastName;
    @Email(message = "Debe ingresr un email valido ")
    private String email;
    @NotBlank(message = "Direccion no valida")
    private String address;
    @NotBlank(message = "Telefono no valido")
    private String cellPhone;
    @NotBlank(message = "Contraseña no valido")
    private String password;

    public User userDtoToUser(){
        return new User(null,this.getEmail(),this.getFirstName(),this.getLastName(),this.getEmail(),this.getAddress(),
                this.getCellPhone(),this.getPassword(), UserType.USER, LocalDateTime.now());
    }
}

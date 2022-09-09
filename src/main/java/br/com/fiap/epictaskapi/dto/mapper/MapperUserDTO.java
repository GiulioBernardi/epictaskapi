package br.com.fiap.epictaskapi.dto.mapper;

import br.com.fiap.epictaskapi.dto.UserDTO;
import br.com.fiap.epictaskapi.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapperUserDTO {
    public UserDTO toDto(User user) {
        Long id = user.getId();
        String name = user.getName();
        String email = user.getEmail();

        return new UserDTO(id, name, email);
    }


}

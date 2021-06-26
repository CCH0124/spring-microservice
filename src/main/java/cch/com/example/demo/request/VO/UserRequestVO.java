package cch.com.example.demo.request.VO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestVO {
    
    private String id;

    @NotEmpty
    private String name;
    
    @NotNull
    private Integer age;
    
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String tel;
}

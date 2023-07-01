package tup.frc.practica.RPS.RPS.dtos.common;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewMatchRequestDTO {

    @NotNull
    private Long playerOneId;

    @NotNull
    private Long playerTwoId;
}
package uni.miskolc.hallgato;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hallgato {
    @NotBlank
    private String nev;
    @Min(18)
    private int eletkor;
    @NotBlank
    private String szak;
}

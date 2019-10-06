package uni.miskolc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uni.miskolc.hallgato.HallgatoService;
import uni.miskolc.hallgato.HallgatoServiceImpl;

@SpringBootApplication
public class MiskolcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiskolcApplication.class, args);
    }
}

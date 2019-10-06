package uni.miskolc.hallgato;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HallgatoService {

    public List<Hallgato> getAll();

    void add(Hallgato hallgato);
}

package uni.miskolc.hallgato;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class HallgatoServiceImpl implements HallgatoService {

    List<Hallgato> hallgatoList = new ArrayList<>();

    @PostConstruct
    void init() {

        this.hallgatoList.add(
                new Hallgato.HallgatoBuilder()
                        .nev("neve")
                        .eletkor(18)
                        .szak("ero")
                        .build()
        );

        this.hallgatoList.add(
                new Hallgato.HallgatoBuilder()
                        .nev("neve 2")
                        .eletkor(28)
                        .szak("fény")
                        .build()
        );
        this.hallgatoList.add(
                new Hallgato.HallgatoBuilder()
                        .nev("neve 3")
                        .eletkor(40)
                        .szak("térerő")
                        .build()
        );
    }

    @Override
    public List<Hallgato> getAll() {
        return hallgatoList;
    }

    @Override
    public void add(Hallgato hallgato) {
hallgatoList.add(hallgato);
    }
}

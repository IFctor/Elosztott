package uni.miskolc.hallgato;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "hallgato")
@Slf4j
public class HallgatoController {

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    public void setHallgatoService(HallgatoService hallgatoService){
        this.hallgatoService=hallgatoService;
    }

    private HallgatoService hallgatoService;

    @GetMapping("add")
    public ModelAndView firstAdd(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("hallgato",new Hallgato());
        mav.setViewName("addHallgato");
        return  mav;
    }

    @PostMapping("add")
    public ModelAndView add(@Valid Hallgato hallgato, BindingResult bindingResult){
        log.debug("hallgato jott {}", hallgato);

        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.addObject("hallgato",hallgato);
            mav.setViewName("addHallgato");

        }
        else{
            hallgatoService.add(hallgato);
            mav.setViewName("redirect:/hallgato");
        }

        return  mav;
    }

    @GetMapping()
    @ResponseBody
    public ModelAndView getHallgatoList() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("appName",appName);

        modelAndView.addObject("hallgatoList", hallgatoService.getAll());
        modelAndView.setViewName("hallgatoLista");
        return modelAndView;
    }

}

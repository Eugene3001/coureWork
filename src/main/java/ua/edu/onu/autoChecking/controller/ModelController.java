package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.edu.onu.autoChecking.dto.BrandDto;
import ua.edu.onu.autoChecking.dto.ModelDto;
import ua.edu.onu.autoChecking.service.BrandService;
import ua.edu.onu.autoChecking.service.ModelService;

import java.util.List;

@Controller
@Slf4j
public class ModelController {
    private ModelService modelService;
    private BrandService brandService;

    @Autowired
    public ModelController(ModelService modelService, BrandService brandService) {
        this.modelService = modelService;
        this.brandService = brandService;
    }

    @GetMapping("/models/main")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_user', 'app_policeman')")
    public String list(Model model) {
        List<ModelDto> list = modelService.list();
        log.info("GET all models: {}", list);

        model.addAttribute("list", list);
        return "models/models";
    }

    @GetMapping("/models/create")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_user', 'app_policeman')")
    public String createPage(Model model) {
        ModelDto modelDto = new ModelDto();
        List<BrandDto> brandDtoList = brandService.list();

        model.addAttribute("brandList", brandDtoList);
        model.addAttribute("model", modelDto);
        return "models/models-create";
    }

    @PostMapping("/models/create")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_user', 'app_policeman')")
    public String create(@ModelAttribute ModelDto request, Model model) {
        ModelDto response = modelService.create(request);
        log.info("CREATE one model: {}", response);
        return "redirect:/models/main";
    }

    @GetMapping("/models/delete/{modelId}")
    @PreAuthorize("hasAuthority('app_admin')")
    public String delete(@PathVariable("modelId") Long modelId, Model model) {
        ModelDto modelDto = modelService.findOne(modelId);
        modelService.delete(modelDto);
        log.info("DELETE one model");
        return "redirect:/models/main";
    }

//    @GetMapping("/models/byMYear")
//    public List<ModelDto> manufactureYearSortedList() {
//        List<ModelDto> response = modelService.manufactureYearSortedList();
//        log.info("GET all models asc (manufacture year): {}", response);
//        return response;
//    }

//    @GetMapping("/models/find")
//    public List<ModelDto> findByCriteria(@RequestParam(required = false)
//                                                 String bodyType,
//                                         @RequestParam(required = false)
//                                         @DateTimeFormat(pattern = "dd-MM-yyyy")
//                                                 Date begin,
//                                         @RequestParam(required = false)
//                                         @DateTimeFormat(pattern = "dd-MM-yyyy")
//                                                 Date end) {
//        List<ModelDto> response = modelService.findByCriteria(bodyType, begin, end);
//        log.info("GET all models by criteria: {}", response);
//        return response;
//    }
}

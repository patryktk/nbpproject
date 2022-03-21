package pl.tkaczyk.nbpproject.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tkaczyk.nbpproject.API.APIController;
import pl.tkaczyk.nbpproject.model.ComputerModel;
import pl.tkaczyk.nbpproject.model.ComputersModelXML;
import pl.tkaczyk.nbpproject.service.ComputerService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@org.springframework.stereotype.Controller
public class ComputerContoller {

    @Autowired
    private ComputerService computerService;
    @Autowired
    private APIController apiController;

    public ComputerContoller(ComputerService computerService, APIController apiController) {
        this.computerService = computerService;
        this.apiController = apiController;
    }

    @GetMapping("/")
    public String showComputers(Model model, @Param("keyword") String keyword){

        return findPaginated(1,"name", "asc", model, keyword);
    }



    @GetMapping("/page/{pageNumber}")
    public String findPaginated(@PathVariable (value = "pageNumber") int pageNumber,
                                @RequestParam(name = "sortField") String sortField,
                                @RequestParam(name = "sortDirection") String sortDirection,
                                Model model, String keyword){
        int pageSize = 4;

        Page<ComputerModel> page = computerService.findPaginatedSearch(pageNumber, pageSize, sortField, sortDirection, keyword);
        List<ComputerModel> computerModelList = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

        model.addAttribute("keyword",keyword);
        model.addAttribute("computerModel", computerModelList);
        return "list";
    }


    @GetMapping("/addComputer")
    public String addComputer(Model model){
        ComputerModel computerModel = new ComputerModel();
        model.addAttribute("computerModel", computerModel);
        return "add";
    }

    @PostMapping("/saveComputer")
    public String saveComputer(@ModelAttribute ("computer") ComputerModel computerModel){
        BigDecimal currency = apiController.getCurrency(computerModel.getDate());
        BigDecimal plnCurrency = computerModel.getPrice_USD().multiply(currency).setScale(2,RoundingMode.CEILING);
        computerService.addComputer(new ComputerModel(computerModel.getId(), computerModel.getName(),computerModel.getDate(), computerModel.getPrice_USD(), plnCurrency));
        return "redirect:/";
    }

    @GetMapping("/editComputer/{id}")
    public String editComputer(@PathVariable(value = "id") Long id, Model model){
        ComputerModel computerModel = computerService.findById(id);
        model.addAttribute("computerModel", computerModel);
        return "update";
    }

    @GetMapping("/deleteComputer/{id}")
    public String deleteComputer(@PathVariable(value = "id") Long id){
        computerService.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/xml",method = RequestMethod.GET,produces = {MediaType.APPLICATION_XML_VALUE}, headers = "Accept=application/xml")
    public ResponseEntity<ComputersModelXML> xml(){
        ComputersModelXML computersModelXML = computerService.getAllComputersXML();
        return ResponseEntity.ok(computersModelXML);
    }




}

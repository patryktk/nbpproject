package pl.tkaczyk.nbpproject.service;

import org.springframework.data.domain.Page;
import pl.tkaczyk.nbpproject.model.ComputerModel;
import pl.tkaczyk.nbpproject.model.ComputersModelXML;

public interface ComputerService {

    void addComputer(ComputerModel computerModel);

    void deleteById(Long id);

    ComputerModel findById(Long id);

    ComputersModelXML getAllComputersXML();

    Page<ComputerModel> findPaginatedSearch(int pageNumber, int pageSize, String sortField, String sortDirection, String keyword);
}

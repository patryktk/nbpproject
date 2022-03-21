package pl.tkaczyk.nbpproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import pl.tkaczyk.nbpproject.model.ComputerModel;
import pl.tkaczyk.nbpproject.model.ComputersModelXML;
import pl.tkaczyk.nbpproject.repo.ComputerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ComputerServiceImpl implements ComputerService {

    @Autowired
    private ComputerRepository computerRepository;


    @Override
    public List<ComputerModel> getAll() {
        return computerRepository.findAll();
    }

    @Override
    public void addComputer(ComputerModel computerModel) {
        computerRepository.save(computerModel);
    }

    @Override
    public void deleteById(Long id) {
        computerRepository.deleteById(id);
    }

    @Override
    public ComputerModel findById(Long id) {
        Optional<ComputerModel> optional = computerRepository.findById(id);
        return optional.get();
    }

    @Override
    public ComputersModelXML getAllComputersXML() {
        ComputersModelXML computersModelXML = new ComputersModelXML();
        computersModelXML.setComputerModelListXML(computerRepository.findAll());
        return computersModelXML;
    }


    @Override
    public Page<ComputerModel> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();


        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return computerRepository.findAll(pageable);
    }

    @Override
    public List<ComputerModel> getComputerByKeyword(String keyword) {
//        if(keyword != null){
//            List<ComputerModel> computerModelList =  computerRepository.findByKeyword(keyword);
//            return computerModelList;
//        }
//        return computerRepository.findAll();
        return null;
    }

    @Override
    public Page<ComputerModel> findPaginatedSearch(int pageNumber, int pageSize, String sortField, String sortDirection, String keyword) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        Page<ComputerModel> computerModelListPage = computerRepository.findAll(pageable);

        if(keyword != null){
           List<ComputerModel> computerModels = computerRepository.findByKeyword(keyword);
           Page<ComputerModel> pageL = new PageImpl<>(computerModels);
           return pageL;
        }

        return computerModelListPage;
    }


}

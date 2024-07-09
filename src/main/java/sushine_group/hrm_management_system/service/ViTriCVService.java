package sushine_group.hrm_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sushine_group.hrm_management_system.model.ViTriCV;
import sushine_group.hrm_management_system.repository.ViTriCVRepository;

import java.util.List;

@Service
public class ViTriCVService {
    @Autowired
    private ViTriCVRepository viTriCVRepository;

    public List<ViTriCV> findAll() {
        return viTriCVRepository.findAll();
    }

    public ViTriCV findById(int idViTri) {
        return viTriCVRepository.findById(idViTri).orElse(null);
    }
}

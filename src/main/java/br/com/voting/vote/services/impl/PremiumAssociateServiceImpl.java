package br.com.voting.vote.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.voting.vote.dtos.AssociateDTO;
import br.com.voting.vote.exception.NotFoundException;
import br.com.voting.vote.models.Associate;
import br.com.voting.vote.repositories.AssociateRepository;
import br.com.voting.vote.services.PremiumAssociateService;
import jakarta.transaction.Transactional;

@Service
public class PremiumAssociateServiceImpl implements PremiumAssociateService {

    private final AssociateRepository repository;

    public PremiumAssociateServiceImpl(AssociateRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void createAssociate(AssociateDTO associateDTO) {
        Associate associate = new Associate();
        associate.setName(associateDTO.getName());
        associate.setCpf(associateDTO.getCpf());

        // Pode ter lógica adicional para associados premium aqui

        repository.save(associate);
    }

    @Override
    public List<Associate> findAll() {
        return repository.findAll();
    }

    @Override
    public Associate findById(String id) {
        return repository.findById(Long.parseLong(id)).orElseThrow(() ->
                new NotFoundException("Associado não encontrado"));
    }

    @Override
    public void deleteAssociate(String id) {
        Associate associate = findById(id);

        if (associate != null) {
            repository.delete(associate);
        }
    }

    @Transactional
    @Override
    public void updateAssociate(AssociateDTO associateDTO, String id) {
        Associate associate = findById(id);
        if (associate != null) {
            associate.setCpf(associateDTO.getCpf());
            associate.setName(associateDTO.getName());
            repository.save(associate);
        }
    }

    @Override
    public void grantPremiumBenefits(String id) {
        Associate associate = findById(id);
        // Aqui você pode implementar a lógica para conceder benefícios premium
        System.out.println("Benefícios premium concedidos ao associado: " + associate.getName());
    }
}

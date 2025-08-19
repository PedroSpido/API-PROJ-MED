package proj.med.API.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import proj.med.API.dto.DadosListagemMedicoDto;
import proj.med.API.repository.MedicoRepository;

@Service
public class MedicoService{

    private MedicoRepository repository;

    public MedicoService(MedicoRepository repository){
        this.repository = repository;
    }

    public Page<DadosListagemMedicoDto> listaMedicos(Pageable pageable){
        return this.repository.findAllByAtivoTrue(pageable);
    }
}

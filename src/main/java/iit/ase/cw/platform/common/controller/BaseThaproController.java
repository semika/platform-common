package iit.ase.cw.platform.common.controller;

import iit.ase.cw.platform.common.exception.ThaproNoDataFoundException;
import iit.ase.cw.platform.common.exception.ThaproValidationException;
import iit.ase.cw.platform.common.context.model.SearchFilter;
import iit.ase.cw.platform.common.context.model.ThaproSearchFilter;
import iit.ase.cw.platform.common.service.BaseThaproService;
import iit.ase.cw.platform.common.type.BaseThaproIdDto;
import iit.ase.cw.platform.common.type.ThaproResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public abstract class BaseThaproController<DTO extends BaseThaproIdDto<ID>, ID> {

    private BaseThaproService<DTO, ID> baseThaproService;

    public BaseThaproController(BaseThaproService baseThaproService) {
        this.baseThaproService = baseThaproService;
    }

    @GetMapping("/version")
    public String getVersion(@SearchFilter ThaproSearchFilter filter) {
        return filter.getModule();
    }

    @GetMapping
    public ResponseEntity<ThaproResponse<DTO>> findAll(@SearchFilter ThaproSearchFilter filter)
        throws ThaproNoDataFoundException {
        ThaproResponse<DTO> resourceResponse = baseThaproService.findAll(filter);
        return ResponseEntity.ok(resourceResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThaproResponse<DTO>> findByID(@PathVariable ID id,
                                                       @SearchFilter ThaproSearchFilter filter)
        throws ThaproNoDataFoundException {
        ThaproResponse<DTO> response = baseThaproService.findById(id, filter);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ThaproResponse<DTO>> create(@RequestBody DTO resource,
                                                     @SearchFilter ThaproSearchFilter filter)
        throws ThaproValidationException {
        resource.setId(null);
        ThaproResponse<DTO> response = baseThaproService.save(resource, filter);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}

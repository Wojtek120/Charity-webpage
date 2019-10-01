package pl.coderslab.charity.model.services;

import java.util.List;

public interface ServiceInterface<D, E> {

    List<D> getAll();
    void saveAll(List<D> dto);
    D getOne(Long id);
    Long save(D dto);
    void update(D dto);
    void deleteById(Long id);
    D convertToDto(E entity);
    E convertToEntity(D dto);
}

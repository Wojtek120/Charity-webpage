package pl.coderslab.charity.model.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.dto.CategoryDto;
import pl.coderslab.charity.model.entities.Category;
import pl.coderslab.charity.model.repositories.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService implements ServiceInterface<CategoryDto, Category> {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;


    public CategoryService(ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void saveAll(List<CategoryDto> dto) {
        categoryRepository.saveAll(dto.stream().map(this::convertToEntity).collect(Collectors.toList()));
    }

    @Override
    public CategoryDto getOne(Long id) {
        return convertToDto(categoryRepository.getOne(id));
    }

    @Override
    public Long save(CategoryDto dto) {
        return categoryRepository.save(convertToEntity(dto)).getId();
    }

    @Override
    public void update(CategoryDto dto) {
        categoryRepository.save(convertToEntity(dto));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto convertToDto(Category entity) {
        return modelMapper.map(entity, CategoryDto.class);
    }

    @Override
    public Category convertToEntity(CategoryDto dto) {
        return modelMapper.map(dto, Category.class);
    }
}

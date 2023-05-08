package world.tina.bulletinboard.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import world.tina.bulletinboard.exception.JsonConversionException;

public class CategoryTree {
	private final List<Category> categories;
	private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

	public CategoryTree() {
		this.categories = new ArrayList<>();
	}

	public void addCategory(Category category) {
		this.categories.add(category);
		if (category.getParentId() != null) {
			Category parent = getCategoryById(category.getParentId());
			if (parent != null) {
				parent.addChildId(category.getId());
			}
		}
	}

	private Category getCategoryById(Long id) {
		return categories.stream()
			.filter(category -> category.getId().equals(id))
			.findFirst().orElse(null);
	}

	public List<Category> searchById(Long id) {
		return categories.stream()
			.filter(category -> category.getId().equals(id))
			.collect(Collectors.toList());
	}

	public List<Category> searchByName(String name) {
		return categories.stream()
			.filter(category -> category.getName().equalsIgnoreCase(name))
			.collect(Collectors.toList());
	}

	public String getCategoryByIdAsJson(Long id) {
		Category category = getCategoryById(id);
		try {
			return objectMapper.writeValueAsString(category);
		} catch (JsonProcessingException e) {
			throw new JsonConversionException(e);
		}
	}

	public String searchByIdAsJson(Long id) {
		List<Category> categoryList = searchById(id);
		try {
			return objectMapper.writeValueAsString(categoryList);
		} catch (JsonProcessingException e) {
			throw new JsonConversionException(e);
		}
	}

	public String searchByNameAsJson(String name) {
		List<Category> categoryList = searchByName(name);
		try {
			return objectMapper.writeValueAsString(categoryList);
		} catch (JsonProcessingException e) {
			throw new JsonConversionException(e);
		}
	}
}

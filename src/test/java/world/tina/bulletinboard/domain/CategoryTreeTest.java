package world.tina.bulletinboard.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CategoryTreeTest {

	private CategoryTree categoryTree;

	@BeforeEach
	public void setUp() {
		categoryTree = new CategoryTree();

		Category 여자 = new Category(1L, "여자", null);
		Category 블랙핑크 = new Category(2L, "블랙핑크", 1L);
		Category 공지사항 = new Category(3L, "공지사항", 2L);
		Category 익명게시판 = new Category(4L, "익명게시판", 2L);
		Category 로제 = new Category(5L, "로제", 2L);
		Category 제니 = new Category(6L, "제니", 2L);

		categoryTree.addCategory(여자);
		categoryTree.addCategory(블랙핑크);
		categoryTree.addCategory(공지사항);
		categoryTree.addCategory(익명게시판);
		categoryTree.addCategory(로제);
		categoryTree.addCategory(제니);
	}

	@Test
	@DisplayName("식별자로 검색에 성공한다.")
	void testSearchById() {
		List<Category> result = categoryTree.searchById(2L);
		assertEquals(1, result.size());
		assertEquals("블랙핑크", result.get(0).getName());
	}

	@Test
	@DisplayName("카테고리 이름으로 검색에 성공한다.")
	void testSearchByName() {
		List<Category> result = categoryTree.searchByName("공지사항");
		assertEquals(1, result.size());
		assertEquals(3L, result.get(0).getId());
	}


	@Test
	@DisplayName("카테고리 식별자 및 카테고리명으로 검색하고, 검색된 결과 값이 해당 카테고리의 하위 카테고리를 모두 담고 있는지 확인한다.")
	public void test_search_category_and_contains_all_subcategories() {
		// ID로 검색
		List<Category> 검색결과_by_id = categoryTree.searchById(1L);
		assertEquals(1, 검색결과_by_id.size());
		assertEquals("여자", 검색결과_by_id.get(0).getName());

		List<Long> 하위카테고리_by_id = 검색결과_by_id.get(0).getChildrenIds();
		assertEquals(1, 하위카테고리_by_id.size());
		assertEquals(2L, 하위카테고리_by_id.get(0));

		// 이름으로 검색
		List<Category> 검색결과_by_name = categoryTree.searchByName("여자");
		assertEquals(1, 검색결과_by_name.size());
		assertEquals(1L, 검색결과_by_name.get(0).getId());

		List<Long> 하위카테고리_by_name = 검색결과_by_name.get(0).getChildrenIds();
		assertEquals(1, 하위카테고리_by_name.size());
		assertEquals(2L, 하위카테고리_by_name.get(0));

		// 하위 카테고리 확인
		List<Category> 블랙핑크_리스트 = categoryTree.searchById(2L);
		assertEquals(1, 블랙핑크_리스트.size());
		Category 블랙핑크 = 블랙핑크_리스트.get(0);
		List<Long> 블랙핑크_하위카테고리 = 블랙핑크.getChildrenIds();
		assertEquals(4, 블랙핑크_하위카테고리.size());
		assertTrue(블랙핑크_하위카테고리.containsAll(Arrays.asList(3L, 4L, 5L, 6L)));
	}


	@Test
	@DisplayName("getCategoryById, searchById, searchByName 결과가 JSON 형태로 반환되는지 확인한다.")
	public void test_json_conversion() {
		// getCategoryByIdAsJson 테스트
		String json_getCategoryById = categoryTree.getCategoryByIdAsJson(1L);
		assertTrue(json_getCategoryById.contains("\"id\":1"));
		assertTrue(json_getCategoryById.contains("\"name\":\"여자\""));
		assertTrue(json_getCategoryById.contains("\"parentId\":null"));

		// searchByIdAsJson 테스트
		String json_searchById = categoryTree.searchByIdAsJson(1L);
		assertTrue(json_searchById.contains("\"id\":1"));
		assertTrue(json_searchById.contains("\"name\":\"여자\""));
		assertTrue(json_searchById.contains("\"parentId\":null"));

		// searchByNameAsJson 테스트
		String json_searchByName = categoryTree.searchByNameAsJson("여자");
		assertTrue(json_searchByName.contains("\"id\":1"));
		assertTrue(json_searchByName.contains("\"name\":\"여자\""));
		assertTrue(json_searchByName.contains("\"parentId\":null"));
	}

}
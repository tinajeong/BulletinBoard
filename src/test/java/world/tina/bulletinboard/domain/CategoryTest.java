package world.tina.bulletinboard.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CategoryTest {

	@Test
	@DisplayName("게시판 대분류 카테고리 생성과 중분류 카테고리 추가에 성공한다.")
	public void test_1st_Category() {
		Category category = new Category(1L, "여자", null);
		assertEquals(1L, category.getId());
		assertEquals("여자", category.getName());
		assertEquals(null, category.getParentId());

		category.addChildId(2L);
		assertEquals(1, category.getChildrenIds().size());
		assertEquals(2L, category.getChildrenIds().get(0));
	}


	@Test
	@DisplayName("게시판 소분류 카테고리 생성에 성공한다.")
	public void test_3rd_Category() {
		Category 대분류 = new Category(1L, "여자", null);
		Category 중분류 = new Category(2L, "블랙핑크", 1L);
		Category 소분류 = new Category(3L, "공지사항", 2L);

		assertEquals(3L, 소분류.getId());
		assertEquals("공지사항", 소분류.getName());
		assertEquals(2L, 소분류.getParentId());


		// 중분류 데이터가 존재하는지 확인
		assertEquals(2L, 중분류.getId());
		assertEquals("블랙핑크", 중분류.getName());
		assertEquals(1L, 중분류.getParentId());

		// 대분류 데이터가 존재하는지 확인
		assertEquals(1L, 대분류.getId());
		assertEquals("여자", 대분류.getName());
		assertEquals(null, 대분류.getParentId());
	}


	@Test
	@DisplayName("중분류 카테고리에 여러 하위 카테고리를 추가하고 리스트 검증을 성공한다.")
	void test_multiple_children_for_2nd_Category() {
		Category 대분류 = new Category(1L, "여자", null);
		Category 중분류 = new Category(2L, "블랙핑크", 1L);

		Category 공지사항 = new Category(3L, "공지사항", 2L);
		Category 익명게시판 = new Category(4L, "익명게시판", 2L);
		Category 로제 = new Category(5L, "로제", 2L);
		Category 제니 = new Category(6L, "제니", 2L);

		중분류.addChildId(공지사항.getId());
		중분류.addChildId(익명게시판.getId());
		중분류.addChildId(로제.getId());
		중분류.addChildId(제니.getId());

		assertEquals(4, 중분류.getChildrenIds().size());
		assertEquals(3L, 중분류.getChildrenIds().get(0));
		assertEquals(4L, 중분류.getChildrenIds().get(1));
		assertEquals(5L, 중분류.getChildrenIds().get(2));
		assertEquals(6L, 중분류.getChildrenIds().get(3));
	}


}
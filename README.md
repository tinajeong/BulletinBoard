# BulletinBoard

parent-child 관계를 가지는 계층적 형태의 게시판 카테고리 자료구조를 구현했습니다.

## 주요 기능

1. **카테고리 생성**: [Category](https://github.com/tinajeong/BulletinBoard/blob/feature/category-tree/src/main/java/world/tina/bulletinboard/domain/Category.java) 클래스를 사용하여 카테고리를 생성할 수 있습니다. 생성된 카테고리는 식별자, 카테고리명, 부모 카테고리 식별자를 가집니다.
2. **카테고리 관계 설정**: 카테고리간의 parent-child 관계는 `parentId`와 `childrenIds`를 사용하여 설정됩니다. `addChildId` 메소드를 사용하여 하위 카테고리를 추가할 수 있습니다.
3. **카테고리 검색**: [CategoryTree](https://github.com/tinajeong/BulletinBoard/blob/feature/category-tree/src/main/java/world/tina/bulletinboard/domain/CategoryTree.java) 클래스를 사용하여 카테고리 식별자 및 카테고리명으로 검색할 수 있습니다. 검색 결과는 해당 카테고리의 하위 카테고리를 모두 포함합니다.
4. **JSON 변환**: Jackson 라이브러리르 이용해 json 형태로 변환합니다.
5. **JUnit 기반 테스트 코드**: 위 기능을 검증할 수 있는 테스트 코드([CategoryTest](https://github.com/tinajeong/BulletinBoard/blob/feature/category-tree/src/test/java/world/tina/bulletinboard/domain/CategoryTest.java), [CategoryTreeTest](https://github.com/tinajeong/BulletinBoard/blob/feature/category-tree/src/test/java/world/tina/bulletinboard/domain/CategoryTreeTest.java))가 작성되어 있습니다.

## 기능 요약

1. `Category` 클래스를 사용하여 카테고리를 생성합니다.
2. 생성된 카테고리를 `CategoryTree`에 추가합니다.
3. `CategoryTree` 클래스의 메소드를 사용하여 카테고리를 검색하거나, JSON 형태로 변환합니다.

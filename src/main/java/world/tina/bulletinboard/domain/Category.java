package world.tina.bulletinboard.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

@Getter
public class Category {

	private final Long id;
	private final String name;
	private final Long parentId;
	private final List<Long> childrenIds;

	public Category(Long id, String name, Long parentId) {
		if (id == null || name == null) {
			throw new IllegalArgumentException("id 와 name은 null 일 수 없습니다.");
		}
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.childrenIds = new ArrayList<>();
	}

	public void addChildId(Long childId) {
		if (childId == null) {
			throw new IllegalArgumentException("childId는 null 일 수 없습니다.");
		}
		this.childrenIds.add(childId);
	}

	public List<Long> getChildrenIds() {
		return Collections.unmodifiableList(childrenIds);
	}
}

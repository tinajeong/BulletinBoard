package world.tina.bulletinboard.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Category {

	private Long id;
	private String name;
	private Long parentId;
	private List<Long> childrenIds;

	public Category(Long id, String name, Long parentId) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.childrenIds = new ArrayList<>();
	}

	public void addChildId(Long childId) {
		this.childrenIds.add(childId);
	}

}

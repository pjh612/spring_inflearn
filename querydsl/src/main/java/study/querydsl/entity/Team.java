package study.querydsl.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;

import java.util.List;


@Getter
@Entity
@ToString(of = {"id","name"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

	@Id
	@GeneratedValue
	@Column(name = "team_id")
	private Long id;

	private String name;
	@OneToMany(mappedBy = "team")
	private List<Member> memberList = new ArrayList<>();

	public Team(String name) {
		this.name = name;
	}
}

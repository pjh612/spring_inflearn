package study.querydsl.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(of ={"id", "name", "age"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "member_id")
	private Long id;

	private String name;

	private int age;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;

	public Member(String name, int age) {
		this.name = name;
		this.age = age;
		this.team = null;
	}

	public Member(Long id) {
		this.id = id;
		this.age = 0;
	}

	public Member(String name, int age, Team team) {
		this.name = name;
		this.age = age;
		if (team != null)
		changeTeam(team);
	}

	private void changeTeam(Team team) {
		this.team= team;
		team.getMemberList().add(this);
	}
}

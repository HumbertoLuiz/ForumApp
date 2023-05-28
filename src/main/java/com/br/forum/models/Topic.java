package com.br.forum.models;

import java.time.LocalDateTime;

import com.br.forum.enums.StatusTopic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Table(name= "topics")
public class Topic {

	@EqualsAndHashCode.Include
	@ToString.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String message;

    @Column(nullable= true)
    private LocalDateTime creationDate;

    @Column(nullable= true)
    private StatusTopic status;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User author;
    
    @ManyToOne
    @JoinColumn(name= "course_id")
    private Course courses;

}

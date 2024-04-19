package com.learning.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "users")
public class User implements Serializable {
    @Id
    private String username;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false )
	@JsonManagedReference
	private Role role;
	
	private String password;
	
	private String fullname;
	
	private String mail;
	
	@OneToMany(mappedBy = "user")
	private List<Cart> carts;
	
	public String getUsername() {
		return username;
	}
	

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserRole> userRoles;

    public User() {
        super();
    }

}

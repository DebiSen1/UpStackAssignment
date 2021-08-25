package org.upgrad.upstac.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.upgrad.upstac.users.models.Gender;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.upgrad.upstac.users.models.AccountStatus;
import org.upgrad.upstac.users.roles.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userName;

    @Column
    @JsonIgnore
    @ToString.Exclude
    private String password;

    private LocalDateTime created;

    private LocalDate dateOfBirth;


    private LocalDateTime updated;

    private String firstName;

    private AccountStatus status;

    @Column(unique = true)
    private String email;


    private String lastName;


    private Gender gender;

    @Column(unique = true)
    private String phoneNumber;
    private String address;

    private Integer pinCode;

    //CascadeType.PERSIST has issues with many to many which makes us not use CascadeType.ALL
    //So Using  other Cascades other than CascadeType.PERSIST
//    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
//    @JoinTable(name = "USER_ROLES", joinColumns = {
//            @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
//            @JoinColumn(name = "ROLE_ID") })
//    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;


    public boolean doesRoleIsDoctor() {

        return doesUserHasRole("DOCTOR");


    }

    public boolean doesUserHasRole(String s) {
        return roles.stream()
                .filter(role -> {
                    return role.getName().equalsIgnoreCase(s);
                })
                .findFirst()
                .isPresent();
    }

    public boolean doesRoleIsUser() {
        return doesUserHasRole("USER");
    }

    public boolean doesRoleIsAuthority() {
        return doesUserHasRole("GOVERNMENT_AUTHORITY");
    }

    public boolean doesRoleIsTester() {
        return doesUserHasRole("TESTER");
    }

    public Integer getAge(){

        if(null != dateOfBirth)
            return LocalDate.now().getYear() - dateOfBirth.getYear();
        else
            return 0;
    }

	public void setUserName(String userName2) {
		// TODO Auto-generated method stub
		this.userName=userName2;
		
	}

	public void setPassword(String encrypted) {
		// TODO Auto-generated method stub
		this.password=encrypted;
		
	}

	public void setRoles(Set<Role> roleFor) {
		// TODO Auto-generated method stub
		this.roles=roleFor;
	}

	public void setCreated(LocalDateTime now) {
		// TODO Auto-generated method stub
		this.created=now;
	}

	public void setUpdated(LocalDateTime now) {
		// TODO Auto-generated method stub
		this.updated=now;
	}

	public void setAddress(Object address2) {
		// TODO Auto-generated method stub
		this.address=(String)address2;
	}

	public void setFirstName(Object firstName2) {
		// TODO Auto-generated method stub
		this.firstName=(String)firstName2;
	}

	public void setLastName(Object lastName2) {
		// TODO Auto-generated method stub
		this.lastName=(String)lastName2;
	}

	public void setEmail(String email2) {
		// TODO Auto-generated method stub
		this.email=email2;
	}

	public void setPhoneNumber(String phoneNumber2) {
		// TODO Auto-generated method stub
		this.phoneNumber=phoneNumber2;
	}

	public void setPinCode(Object pinCode2) {
		// TODO Auto-generated method stub
		this.pinCode=(Integer)pinCode2;
	}

	public void setGender(Object gender2) {
		// TODO Auto-generated method stub
		this.gender=(Gender)gender2;
	}

	public void setDateOfBirth(LocalDate dateFromString) {
		// TODO Auto-generated method stub
		this.dateOfBirth=dateFromString;
	}

	public void setStatus(AccountStatus approved) {
		// TODO Auto-generated method stub
		this.status=approved;
	}

	public String getUserName() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	public AccountStatus getStatus() {
		// TODO Auto-generated method stub
		return this.status;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	public Set<Role> getRoles() {
		// TODO Auto-generated method stub
		return this.roles;
	}

	public void setId(long l) {
		// TODO Auto-generated method stub
		this.id=id;
	}
}

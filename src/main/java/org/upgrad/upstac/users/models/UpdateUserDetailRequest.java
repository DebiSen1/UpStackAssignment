package org.upgrad.upstac.users.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateUserDetailRequest {

    @ApiModelProperty(example = "MK")
    private String firstName;


    @ApiModelProperty(example = "Gandhi")
    private String lastName;

    @ApiModelProperty(example = "newuser@upgrad.com")
    private String email="";

    @ApiModelProperty(example = "+91956567687")
    private String phoneNumber="";

	public String getFirstName() {
		// TODO Auto-generated method stub
		return this.firstName;
	}

	public String getLastName() {
		// TODO Auto-generated method stub
		return this.lastName;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}

	public String getPhoneNumber() {
		// TODO Auto-generated method stub
		return this.phoneNumber;
	}

}

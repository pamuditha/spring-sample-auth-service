package org.codelanka.authserver.models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name = "User")
@Data
public class UserRegistation extends User implements Serializable {
    @NotNull
    private String email;
    @NotNull
    private String fName;
    @NotNull
    private String lName;
    @NotNull
    private String mobileNumber;
}
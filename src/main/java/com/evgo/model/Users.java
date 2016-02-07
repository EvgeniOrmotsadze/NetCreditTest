package com.evgo.model;

import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by root_pc on 2/6/2016.
 */

@Configuration
@Entity
@NamedQueries({
        @NamedQuery(name="findUsers",
                query= "FROM Users e " +
                        "WHERE e.name = :name AND " +
                        "      e.password = :pass")

})
public class Users implements Serializable {

        @Id
        @GeneratedValue
        private int id;

        private String name;

        private String password;

        private String phone;

        private Date birthday;

        private int monthSalary;

        private int currentLiability;

        public Users() {}

     public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public int getMonthSalary() {
            return monthSalary;
        }

        public void setMonthSalary(int monthSalary) {
            this.monthSalary = monthSalary;
        }

        public int getCurrentLiability() {
            return currentLiability;
        }

        public void setCurrentLiability(int currentLiability) {
            this.currentLiability = currentLiability;
        }
}




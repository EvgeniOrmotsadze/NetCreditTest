package com.evgo.model;

import org.springframework.context.annotation.Configuration;

import javax.persistence.*;

/**
 * Created by root_pc on 2/6/2016.
 */

@Configuration
@Entity
@NamedQueries({
        @NamedQuery(name="findUsers",
                query="SELECT e.id, e.name, e.password, e.phone, e.birthday, e.monthSalary, e.currentLiability " +
                        "FROM Users e " +
                        "WHERE e.name = :name AND " +
                        "      e.password = :pass")
})
public class Users {

        @Id
      /*  @GeneratedValue(strategy = GenerationType.AUTO)*/
        private int id;

        private String name;

        private String password;

        private String phone;

        private String birthday;

        private String monthSalary;

        private String currentLiability;

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

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getMonthSalary() {
            return monthSalary;
        }

        public void setMonthSalary(String monthSalary) {
            this.monthSalary = monthSalary;
        }

        public String getCurrentLiability() {
            return currentLiability;
        }

        public void setCurrentLiability(String currentLiability) {
            this.currentLiability = currentLiability;
        }
}




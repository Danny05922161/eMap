package com.esunbank.emap.database;

import com.esunbank.emap.DTO.Appointment;
import com.esunbank.emap.DTO.LoginUser;
import com.esunbank.emap.DTO.User;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private static DataStore dataStore;
    private String loginAccount;
    private List<User> users = new ArrayList<>();
    private List<LoginUser> loginUsers = new ArrayList<>();
    private List<Appointment> appointmentList = new ArrayList<>();

    synchronized static public DataStore getInstance() {
        if (dataStore == null) {
            dataStore = new DataStore();
        }
        return dataStore;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<LoginUser> getLoginUsers() {
        return loginUsers;
    }

    public void appendLoginUsers(LoginUser loginUsers) {
        this.loginUsers.add(loginUsers);
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void appendAppointmentList(Appointment appointment) {
        this.appointmentList.add(appointment);
    }
}

package com.wally.hiread.model.user;

import java.util.List;

public class UserBadgePanel {

    List<UserBadge> userWeekBadges;
    List<UserBadge> userAllBadges;

    List<User> badgeAcademicWeekUsers;
    List<User> badgeBehaviorWeekUsers;
    List<User> badgeAcademicAllUsers;
    List<User> badgeBehaviorAllUsers;

    public List<User> getBadgeAcademicWeekUsers() {
        return badgeAcademicWeekUsers;
    }

    public void setBadgeAcademicWeekUsers(List<User> badgeAcademicWeekUsers) {
        this.badgeAcademicWeekUsers = badgeAcademicWeekUsers;
    }

    public List<User> getBadgeBehaviorWeekUsers() {
        return badgeBehaviorWeekUsers;
    }

    public void setBadgeBehaviorWeekUsers(List<User> badgeBehaviorWeekUsers) {
        this.badgeBehaviorWeekUsers = badgeBehaviorWeekUsers;
    }

    public List<User> getBadgeAcademicAllUsers() {
        return badgeAcademicAllUsers;
    }

    public void setBadgeAcademicAllUsers(List<User> badgeAcademicAllUsers) {
        this.badgeAcademicAllUsers = badgeAcademicAllUsers;
    }

    public List<User> getBadgeBehaviorAllUsers() {
        return badgeBehaviorAllUsers;
    }

    public void setBadgeBehaviorAllUsers(List<User> badgeBehaviorAllUsers) {
        this.badgeBehaviorAllUsers = badgeBehaviorAllUsers;
    }

    public List<UserBadge> getUserWeekBadges() {
        return userWeekBadges;
    }

    public void setUserWeekBadges(List<UserBadge> userWeekBadges) {
        this.userWeekBadges = userWeekBadges;
    }

    public List<UserBadge> getUserAllBadges() {
        return userAllBadges;
    }

    public void setUserAllBadges(List<UserBadge> userAllBadges) {
        this.userAllBadges = userAllBadges;
    }
}
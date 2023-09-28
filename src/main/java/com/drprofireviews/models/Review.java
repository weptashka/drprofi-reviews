package com.drprofireviews.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int grade;
    boolean good_time;
    boolean good_admin;
    boolean good_doctor;
    boolean good_fast;
    boolean good_other;
    boolean bad_time;
    boolean bad_admin;
    boolean bad_doctor;
    boolean bad_interior;
    boolean bad_other;
    private String review_text;
    private String review_text_bad;
    private String mobile_number = "+375 ";
    private int friend_grade;
    private LocalDateTime review_date;
    private String datestring;
    private boolean isBackArrowPressed;



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public boolean isGood_time() {
        return this.good_time;
    }

    public void setGood_time(boolean good_time) {
        this.good_time = good_time;
    }

    public boolean isGood_admin() {
        return this.good_admin;
    }

    public void setGood_admin(boolean good_admin) {
        this.good_admin = good_admin;
    }

    public boolean isGood_doctor() {
        return this.good_doctor;
    }

    public void setGood_doctor(boolean good_doctor) {
        this.good_doctor = good_doctor;
    }

    public boolean isGood_fast() {
        return this.good_fast;
    }

    public void setGood_fast(boolean good_fast) {
        this.good_fast = good_fast;
    }
    public boolean isGood_other() {
        return good_other;
    }

    public void setGood_other(boolean good_other) {
        this.good_other = good_other;
    }

    public boolean isBad_time() {
        return this.bad_time;
    }

    public void setBad_time(boolean bad_time) {
        this.bad_time = bad_time;
    }

    public boolean isBad_admin() {
        return this.bad_admin;
    }

    public void setBad_admin(boolean bad_admin) {
        this.bad_admin = bad_admin;
    }

    public boolean isBad_doctor() {
        return this.bad_doctor;
    }

    public void setBad_doctor(boolean bad_doctor) {
        this.bad_doctor = bad_doctor;
    }

    public boolean isBad_interior() {
        return this.bad_interior;
    }

    public void setBad_interior(boolean bad_interior) {
        this.bad_interior = bad_interior;
    }
    public boolean isBad_other() {
        return bad_other;
    }

    public void setBad_other(boolean bad_other) {
        this.bad_other = bad_other;
    }

    public String getReview_text() {
        return this.review_text;
    }

    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }

    public String getReview_text_bad() {
        return review_text_bad;
    }

    public void setReview_text_bad(String review_text_bad) {
        this.review_text_bad = review_text_bad;
    }

    public String getMobile_number() {
        return this.mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }
    public int getFriend_grade() {
        return friend_grade;
    }

    public void setFriend_grade(int friend_grade) {
        this.friend_grade = friend_grade;
    }

    public LocalDateTime getReview_date() {
        return this.review_date;
    }

    public void setReview_date(LocalDateTime review_date) {
        this.review_date = review_date;
    }

    public String getDatestring() {
        return this.datestring;
    }

    public void setDatestring(String datestring) {
        this.datestring = datestring;
    }

    public boolean isBackArrowPressed() {
        return isBackArrowPressed;
    }

    public void setBackArrowPressed(boolean backArrowPressed) {
        isBackArrowPressed = backArrowPressed;
    }


    public Review() {
    }

    public Review(int grade) {
        this.grade = grade;
    }

    public Review(int grade, String review_text) {
        this.grade = grade;
        this.review_text = review_text;
    }
}

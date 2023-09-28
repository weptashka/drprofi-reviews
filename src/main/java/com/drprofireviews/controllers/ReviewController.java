package com.drprofireviews.controllers;

import com.drprofireviews.models.Review;
import com.drprofireviews.repositories.ReviewRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewController() {
    }

    @GetMapping({"/review"})
    public String review(Model model) {
        return "review";
    }

    @PostMapping({"/review"})
    public String reviewAdd(@RequestParam String review_text, @RequestParam int grade, Model model) {
        Review review = new Review(grade, review_text);
        this.reviewRepository.save(review);
        return "redirect:/hello";
    }

    //////////////////////////REVIEW-LIST///////////////////////////
    @GetMapping({"/review-list"})
    public String reviewList(Model model) {
        Iterable<Review> reviews = this.reviewRepository.findAll();
        model.addAttribute("reviews", reviews);
        return "review/review-list";
    }


    //////////////////////////REVIEW-GRADE///////////////////////////

    @GetMapping({"/review-grade"})
    public String reviewGrade(Model model) {
        return "review/review-grade";
    }

    @PostMapping({"/review-grade"})
    public String reviewGradeAdd(@RequestParam int grade, Model model) {
        Review currentReview = new Review(grade);
        LocalDateTime date = LocalDateTime.now();
        currentReview.setReview_date(date);
        currentReview.setDatestring(date.toString());
        this.reviewRepository.save(currentReview);
        String return_page;
        if (currentReview.getGrade() == 5 || currentReview.getGrade() == 4 || currentReview.getGrade() == 3)
            return_page = "redirect:/review-like/" + currentReview.getDatestring();
        else return_page = "redirect:/review-dislike/" + currentReview.getDatestring();
        return return_page;
    }


    //////////////////////////REVIEW-GRADE-EDIT///////////////////////////

    @GetMapping({"/review-grade-edit/{datestring}"})
    public String reviewGradeChange(@PathVariable("datestring") String datestring, Model model) {
        Review currentReview = this.reviewRepository.findByDatestring(datestring);
        model.addAttribute("currentReview", currentReview);
        this.reviewRepository.save(currentReview);
        return "review/review-grade-edit";
    }

    @PostMapping({"/review-grade-edit/{datestring}"})
    public String reviewGradeAddChange(@PathVariable("datestring") String datestring, @RequestParam int grade, Model model) {
        Review currentReview = this.reviewRepository.findByDatestring(datestring);
        currentReview.setGrade(grade);
        this.reviewRepository.save(currentReview);
        String return_page;
        if (currentReview.getGrade() >= 3) return_page = "redirect:/review-like/" + currentReview.getDatestring();
        else return_page = "redirect:/review-dislike/" + currentReview.getDatestring();
        return return_page;
    }


    //////////////////////////REVIEW-LIKE///////////////////////////

    @GetMapping({"/review-like/{datestring}"})
    public String reviewLike(@PathVariable("datestring") String datestring, Model model) {
        Review currentReview = this.reviewRepository.findByDatestring(datestring);
        model.addAttribute("currentReview", currentReview);
        return "review/review-like";
    }

    @PostMapping({"/review-like/{datestring}"})
    public String reviewLikeAdd(@PathVariable("datestring") String datestring, @RequestParam(required = false) boolean isBack, @RequestParam(required = false) String good_time, @RequestParam(required = false) String good_admin, @RequestParam(required = false) String good_doctor, @RequestParam(required = false) String good_fast, @RequestParam(required = false) String good_other, Model model) {
        Review currentReview = this.reviewRepository.findByDatestring(datestring);
        currentReview.setGood_time(good_time != null);
        currentReview.setGood_admin(good_admin != null);
        currentReview.setGood_doctor(good_doctor != null);
        currentReview.setGood_fast(good_fast != null);
        currentReview.setGood_other(good_other != null);
        currentReview.setBackArrowPressed(isBack);
        this.reviewRepository.save(currentReview);
        String return_page;
        if (currentReview.isBackArrowPressed())
            return_page = "redirect:/review-grade-edit/" + currentReview.getDatestring();
        else if ((currentReview.getGrade() >= 3) && currentReview.isGood_other() == true)
            return_page = "redirect:/review-text/" + currentReview.getDatestring();
        else if (currentReview.getGrade() == 5 && currentReview.isGood_other() != true)
            return_page = "redirect:/review-friend/" + currentReview.getDatestring();
        else return_page = "redirect:/review-dislike/" + currentReview.getDatestring();
        return return_page;
    }


    //////////////////////////REVIEW-TEXT///////////////////////////

    @GetMapping({"/review-text/{datestring}"})
    public String reviewText(@PathVariable("datestring") String datestring, Model model) {
        Review currentReview = this.reviewRepository.findByDatestring(datestring);
        model.addAttribute("currentReview", currentReview);
        return "review/review-text";
    }

    @PostMapping({"/review-text/{datestring}"})
    public String reviewTextAdd(@PathVariable("datestring") String datestring, @RequestParam(required = false) boolean isBack, @RequestParam String review_text, Model model) {
        Review currentReview = this.reviewRepository.findByDatestring(datestring);
        currentReview.setReview_text(review_text);
        currentReview.setBackArrowPressed(isBack);
        //save data
        this.reviewRepository.save(currentReview);
        String return_page;
        //to previous page
        if (currentReview.isBackArrowPressed()) return_page = "redirect:/review-like/" + currentReview.getDatestring();
            //to next page
        else if (currentReview.getGrade() == 5)
            return_page = "redirect:/review-friend/" + currentReview.getDatestring();
        else return_page = "redirect:/review-dislike/" + currentReview.getDatestring();
        return return_page;
    }


    //////////////////////////REVIEW-DISLIKE///////////////////////////

    @GetMapping({"/review-dislike/{datestring}"})
    public String reviewDislike(@PathVariable("datestring") String datestring, Model model) {
        Review currentReview = this.reviewRepository.findByDatestring(datestring);
        model.addAttribute("currentReview", currentReview);
        return "review/review-dislike";
    }

    @PostMapping({"/review-dislike/{datestring}"})
    public String reviewDislikeAdd(@PathVariable("datestring") String datestring, @RequestParam(required = false) boolean isBack, @RequestParam(required = false) String bad_time, @RequestParam(required = false) String bad_admin, @RequestParam(required = false) String bad_doctor, @RequestParam(required = false) String bad_interior, @RequestParam(required = false) String bad_other, Model model) {
        Review currentReview = this.reviewRepository.findByDatestring(datestring);
        currentReview.setBad_time(bad_time != null);
        currentReview.setBad_admin(bad_admin != null);
        currentReview.setBad_doctor(bad_doctor != null);
        currentReview.setBad_interior(bad_interior != null);
        currentReview.setBad_other(bad_other != null);
        currentReview.setBackArrowPressed(isBack);
        this.reviewRepository.save(currentReview);
        String return_page;
        if (currentReview.isBackArrowPressed() && currentReview.getGrade() < 3)
            return_page = "redirect:/review-grade-edit/" + currentReview.getDatestring();
        else if (currentReview.isBackArrowPressed() && currentReview.getGrade() >= 3 && currentReview.isGood_other())
            return_page = "redirect:/review-text/" + currentReview.getDatestring();
        else if (currentReview.isBackArrowPressed() && currentReview.getGrade() >= 3)
            return_page = "redirect:/review-like/" + currentReview.getDatestring();
        else if (currentReview.isBad_other())
            return_page = "redirect:/review-text-bad/" + currentReview.getDatestring();
        else return_page = "redirect:/review-mobile/" + currentReview.getDatestring();
        return return_page;
    }


    //////////////////////////REVIEW-TEXT-BAD///////////////////////////

    @GetMapping({"/review-text-bad/{datestring}"})
    public String reviewTextBad(@PathVariable("datestring") String datestring, Model model) {
        Review currentReview = this.reviewRepository.findByDatestring(datestring);
        model.addAttribute("currentReview", currentReview);
        return "review/review-text-bad";
    }

    @PostMapping({"/review-text-bad/{datestring}"})
    public String reviewTextBadAdd(@PathVariable("datestring") String datestring, @RequestParam(required = false) boolean isBack, @RequestParam String review_text_bad, Model model) {
        Review currentReview = this.reviewRepository.findByDatestring(datestring);
        currentReview.setReview_text_bad(review_text_bad);
        currentReview.setBackArrowPressed(isBack);
        this.reviewRepository.save(currentReview);
        String return_page;
        if (currentReview.isBackArrowPressed())
            return_page = "redirect:/review-dislike/" + currentReview.getDatestring();
        else return_page = "redirect:/review-mobile/" + currentReview.getDatestring();
        return return_page;
    }


    //////////////////////////REVIEW-MOBILE///////////////////////////

    @GetMapping({"/review-mobile/{datestring}"})
    public String reviewMobile(@PathVariable("datestring") String datestring, Model model) {
        Review currentReview = this.reviewRepository.findByDatestring(datestring);
        model.addAttribute("currentReview", currentReview);
        return "review/review-mobile";
    }

    @PostMapping({"/review-mobile/{datestring}"})
    public String reviewMobileAdd(@PathVariable("datestring") String datestring, @RequestParam(required = false) boolean isBack, @RequestParam String mobile_number, Model model) {
        Review currentReview = this.reviewRepository.findByDatestring(datestring);
        currentReview.setMobile_number(mobile_number);
        currentReview.setBackArrowPressed(isBack);
        this.reviewRepository.save(currentReview);
        String return_page;
        if (currentReview.isBackArrowPressed() && currentReview.isBad_other())
            return_page = "redirect:/review-text-bad/" + currentReview.getDatestring();
        else return_page = "redirect:/review-friend/" + currentReview.getDatestring();
        return return_page;
    }


    //////////////////////////REVIEW-FRIEND///////////////////////////

    @GetMapping({"/review-friend/{datestring}"})
    public String reviewFriend(@PathVariable("datestring") String datestring, Model model) {
        Review currentReview = this.reviewRepository.findByDatestring(datestring);
        model.addAttribute("currentReview", currentReview);
        this.reviewRepository.save(currentReview);
        return "review/review-friend";
    }

    @PostMapping({"/review-friend/{datestring}"})
    public String reviewFriendAdd(@PathVariable("datestring") String datestring, @RequestParam(required = false) boolean isBack, @RequestParam(defaultValue = "0") int friend_grade, Model model) {
        Review currentReview = this.reviewRepository.findByDatestring(datestring);
        currentReview.setBackArrowPressed(isBack);
        String return_page;
        if (currentReview.isBackArrowPressed()) {
            if (currentReview.getGrade() == 5 && currentReview.isGood_other())
                return_page = "redirect:/review-text/" + currentReview.getDatestring();
            else if (currentReview.getGrade() == 5)
                return_page = "redirect:/review-like/" + currentReview.getDatestring();
            else
                return_page = "redirect:/review-mobile/" + currentReview.getDatestring();
        } else {
            return_page = "review/thanks";
            // deleting unuseful data
            //if 5 grade
            if (currentReview.getGrade() == 5) {
                currentReview.setBad_time(false);
                currentReview.setBad_admin(false);
                currentReview.setBad_doctor(false);
                currentReview.setBad_interior(false);
                currentReview.setBad_other(false);
                currentReview.setMobile_number(null);
                currentReview.setReview_text_bad(null);
                if (currentReview.isGood_other() == false) {
                    currentReview.setReview_text(null);
                }
            }
            //if 4 or 3 grade
            if (currentReview.getGrade() == 4 || currentReview.getGrade() == 3) {
                if (!currentReview.isGood_other()) {
                    currentReview.setReview_text(null);
                }
                if (!currentReview.isBad_other()) {
                    currentReview.setReview_text_bad(null);
                }
            }
            //if 2 or 1 grade
            if (currentReview.getGrade() == 2 || currentReview.getGrade() == 1) {
                currentReview.setGood_time(false);
                currentReview.setGood_admin(false);
                currentReview.setGood_doctor(false);
                currentReview.setGood_fast(false);
                currentReview.setGood_other(false);
                currentReview.setReview_text(null);
                if (!currentReview.isBad_other()) {
                    currentReview.setReview_text_bad(null);
                }
            }
        }
        currentReview.setFriend_grade(friend_grade);
        this.reviewRepository.save(currentReview);
        return return_page;
    }


    //////////////////////////THANKS///////////////////////////

    @GetMapping({"/thanks"})
    public String reviewThanks(Model model) {
        return "review/thanks";
    }
}

package com.crystalzord.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String rating;

    @Column(nullable = false)
    private String description;

    protected Review() {

    }

    public Review(String rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Review{" +
                "rating='" + rating + '\'' +
                "description='" + description + '\'' +
                '}';
    }

}

package com.match.dev.entity;

import jakarta.persistence.*;

@Entity
public class MatchOdds {

    @Id
    @SequenceGenerator(name = "match_odds_seq", sequenceName = "match_odds_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_odds_seq")
    private Long id;

    private String specifier;

    private float odd;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecifier() {
        return specifier;
    }

    public void setSpecifier(String specifier) {
        this.specifier = specifier;
    }

    public float getOdd() {
        return odd;
    }

    public void setOdd(float odd) {
        this.odd = odd;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}

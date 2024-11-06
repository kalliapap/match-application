package com.match.dev.entity;

import jakarta.persistence.*;

@Entity
public class MatchOdds {

    @Id
    @SequenceGenerator(name = "match_odds_seq", sequenceName = "match_odds_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_odds_seq")
    private long id;

    private String specifier;

    private float odd;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false, insertable=false, updatable=false)
    private Match match;

    public MatchOdds() {
    }

    private MatchOdds(Builder builder) {
        setId(builder.id);
        setSpecifier(builder.specifier);
        setOdd(builder.odd);
        setMatch(builder.match);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public static class Builder {
        private long id;
        private String specifier;
        private float odd;
        private Match match;

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withSpecifier(String specifier) {
            this.specifier = specifier;
            return this;
        }

        public Builder withOdd(float odd) {
            this.odd = odd;
            return this;
        }

        public Builder withMatch(Match match) {
            this.match = match;
            return this;
        }

        public MatchOdds build() {
            return new MatchOdds(this);
        }
    }
}
